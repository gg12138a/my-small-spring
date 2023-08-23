package org.example.beans.factory;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings({"rawtypes"})
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
}
