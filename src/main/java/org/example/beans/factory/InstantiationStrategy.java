package org.example.beans.factory;


import org.example.beans.BeansException;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;

/**
 * Bean实例化策略，例如JDK反射或者Cglib等
 */
public interface InstantiationStrategy {

    @SuppressWarnings({"rawtypes"})
    Object instantiate(BeanDefinition beanDefinition, String beanName, @Nullable Constructor ctor, Object[] args) throws BeansException;
}
