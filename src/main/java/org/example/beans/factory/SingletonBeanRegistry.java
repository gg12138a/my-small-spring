package org.example.beans.factory;


/**
 * 提供<b>获取</b><b>单例</b> Bean 的功能
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
