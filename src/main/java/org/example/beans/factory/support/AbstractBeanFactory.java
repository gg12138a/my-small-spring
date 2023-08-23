package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.BeanFactory;
import org.example.beans.factory.BeanDefinition;
import org.jetbrains.annotations.Nullable;

/**
 * 核心功能： 获取bean对象
 */
public abstract class AbstractBeanFactory
        /* get和add单例对象的功能 */ extends DefaultSingletonBeanRegistry
        /* get bean的功能 */ implements BeanFactory {

    /**
     * 经典的模板方法模式
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... ctorArgs) throws BeansException {
        return doGetBean(beanName, ctorArgs);
    }

    @SuppressWarnings({"unchecked cast"})
    private <T> T doGetBean(String beanName, @Nullable Object[] ctorArgs) {
        Object bean = super.getSingleton(beanName);
        if (bean != null) {
            return (T) bean;
        } else {
            BeanDefinition beanDefinition = this.getBeanDefinition(beanName);
            return (T) this.createBean(beanName, beanDefinition, ctorArgs);
        }
    }


    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 事实上，创建了新的Bean对象并将put进了map
     * <p>
     * TODO: 将put的动作上移至此公共抽象类
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, @Nullable Object[] ctorArgs) throws BeansException;
}
