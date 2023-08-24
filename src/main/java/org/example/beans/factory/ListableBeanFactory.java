package org.example.beans.factory;


import org.example.beans.BeansException;

import java.util.Map;

/**
 * 提供可枚举出BeanFactory中已注册的BeanDef的信息
 */
public interface ListableBeanFactory extends BeanFactory {

    <T> Map<String, T> getBeansOfType(Class<T> beanClazz) throws BeansException;

    String[] getBeanDefinitionNames();
}
