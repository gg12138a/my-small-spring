package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.BeanDefinition;

/**
 * 核心功能： 根据BeanDef创建bean对象
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        super.addSingleton(beanName, bean);

        return bean;
    }
}
