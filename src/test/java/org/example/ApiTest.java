package org.example;

import org.example.bean.UserService;
import org.example.beans.factory.BeanDefinition;
import org.example.beans.factory.BeanFactory;
import org.example.beans.factory.support.DefaultListableBeanFactory;
import org.example.beans.factory.support.SimpleJDKInstantiationStrategy;
import org.junit.Test;


public class ApiTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 1. 定义BeanDef
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        // 2. 注册
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "xiaoming");
        userService.queryInfo();

        // 4. 单例测试
        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        System.out.println(userService == userServiceSingleton);
    }

    @Test
    public void testBeanFactory_JDkProxyInstantiation() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.setInstantiationStrategy(new SimpleJDKInstantiationStrategy());

        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));

        UserService userService = (UserService) beanFactory.getBean("userService", "xiaoming");
        userService.queryInfo();

        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        System.out.println(userService == userServiceSingleton);
    }
}
