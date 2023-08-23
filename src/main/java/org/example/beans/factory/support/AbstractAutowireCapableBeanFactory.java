package org.example.beans.factory.support;

import lombok.Getter;
import lombok.Setter;
import org.example.beans.BeansException;
import org.example.beans.factory.BeanDefinition;
import org.example.beans.factory.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Optional;

/**
 * 核心功能： 根据BeanDef创建bean对象
 */

@Getter
@Setter
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubClassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] ctorArgs) throws BeansException {
        Object bean;

        try {
            bean = this.createBeanInstance(beanDefinition, beanName, ctorArgs);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        super.addSingleton(beanName, bean);

        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] ctorArgs) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        Constructor<?> ctorToUse = null;
        if (ctorArgs != null) {
            Optional<Constructor<?>> matchedCtor = Arrays.stream(declaredConstructors)
                    // TODO: 不仅对构造函数的长度进行比较，还对构造函数的参数类型进行比较
                    .filter(ctor -> ctor.getParameterTypes().length == ctorArgs.length)
                    .findFirst();

            if (matchedCtor.isPresent()) {
                ctorToUse = matchedCtor.get();
            }
        }

        return this.instantiationStrategy.instantiate(beanDefinition, beanName, ctorToUse, ctorArgs);
    }

}
