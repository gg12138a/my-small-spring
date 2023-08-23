package org.example.beans.factory;

import org.example.beans.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
}
