package org.example.beans.factory;

import lombok.Getter;

@Getter
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
}
