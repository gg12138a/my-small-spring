package org.example.beans.factory;

import org.example.beans.BeansException;
import org.example.core.io.Resource;
import org.example.core.io.ResourceLoader;

/**
 * 核心功能： 从资源文件中加载BeanDef.
 * <p>
 * Tip： 上面两个方法可忽略
 */
public interface BeanDefinitionReader {

    ResourceLoader getResourceLoader();

    BeanDefinitionRegistry getRegistry();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
