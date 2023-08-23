package org.example.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;
import org.example.beans.BeansException;
import org.example.beans.PropertyValue;
import org.example.beans.PropertyValues;
import org.example.beans.factory.BeanDefinition;
import org.example.beans.factory.BeanReference;
import org.example.beans.factory.InstantiationStrategy;
import org.jetbrains.annotations.Nullable;

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
    protected Object createBean(String beanName, BeanDefinition beanDefinition, @Nullable Object[] ctorArgs) throws BeansException {
        Object bean;

        try {
            bean = this.createBeanInstance(beanDefinition, beanName, ctorArgs);
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        super.addSingleton(beanName, bean);

        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, @Ignore String beanName, @Nullable Object[] ctorArgs) {
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

    /**
     * 进行属性填充
     * <p>
     * TODO: 解决循环依赖问题
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue pv : propertyValues.toArray()) {
                String propName = pv.getName();
                Object val = pv.getVal();

                if (val instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) val;
                    val = super.getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, propName, val);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

}
