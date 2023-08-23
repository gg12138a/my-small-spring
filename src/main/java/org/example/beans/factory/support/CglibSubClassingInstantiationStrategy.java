package org.example.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.example.beans.BeansException;
import org.example.beans.factory.BeanDefinition;
import org.example.beans.factory.InstantiationStrategy;

import java.lang.reflect.Constructor;

public class CglibSubClassingInstantiationStrategy implements InstantiationStrategy {
    @Override
    @SuppressWarnings({"rawtypes"})
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if (ctor == null) {
            return enhancer.create();
        } else {
            return enhancer.create(ctor.getParameterTypes(), args);
        }
    }
}
