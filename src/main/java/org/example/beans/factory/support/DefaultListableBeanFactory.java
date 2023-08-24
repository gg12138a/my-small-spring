package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.BeanDefinition;
import org.example.beans.factory.BeanDefinitionRegistry;
import org.example.beans.factory.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心功能： 管理（get, add）BeanDef
 */
public class DefaultListableBeanFactory
        extends AbstractAutowireCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }

        return beanDefinition;
    }

    @Override
    @SuppressWarnings({"unchecked cast", "rawtypes"})
    public <T> Map<String, T> getBeansOfType(Class<T> beanClazz) throws BeansException {
        Map<String, T> ret = new HashMap<>();

        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanDefClazz = beanDefinition.getBeanClass();
            if (beanClazz.isAssignableFrom(beanDefClazz)) {
                ret.put(beanName, (T) getBean(beanName));
            }
        });

        return ret;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
