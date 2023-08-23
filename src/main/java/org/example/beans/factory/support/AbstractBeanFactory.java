package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.BeanFactory;
import org.example.beans.factory.BeanDefinition;

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
        Object bean = super.getSingleton(beanName);

        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
