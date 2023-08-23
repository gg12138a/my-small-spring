package org.example.beans.factory;

/**
 * 提供：注册BeanDef的功能
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
