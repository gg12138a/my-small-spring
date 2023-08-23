package org.example.beans.factory.support;

import org.example.beans.factory.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心功能： 管理单例bean对象
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object obj) {
        this.singletonObjects.put(beanName, obj);
    }
}
