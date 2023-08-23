package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.BeanDefinition;
import org.example.beans.factory.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleJDKInstantiationStrategy implements InstantiationStrategy {
    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (ctor != null) {
                // 使用有参数构造器
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                // 使用无参数构造器
                return clazz.newInstance();
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
