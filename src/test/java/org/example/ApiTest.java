package org.example;

import org.example.bean.OrderDao;
import org.example.bean.OrderService;
import org.example.bean.UserService;
import org.example.beans.PropertyValue;
import org.example.beans.PropertyValues;
import org.example.beans.factory.BeanDefinition;
import org.example.beans.factory.BeanReference;
import org.example.beans.factory.support.DefaultListableBeanFactory;
import org.example.beans.factory.support.SimpleJDKInstantiationStrategy;
import org.junit.Test;


public class ApiTest {

    /**
     * 基本的使用流程测试：
     * 1. 定义BeanDef
     * 2. 注册BeanDef
     * 3. 获取Bean对象
     */
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

    /**
     * 使用JDK反射生成Bean对象，而非默认的Cglib代理
     */
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

    /**
     * 测试属性注入
     */
    @Test
    public void testPropertyInjection() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // register orderDao
        beanFactory.registerBeanDefinition("orderDao", new BeanDefinition(OrderDao.class));

        // register orderService
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("plainVal", 23));
        propertyValues.addPropertyValue(new PropertyValue("orderDao", new BeanReference("orderDao")));
        beanFactory.registerBeanDefinition("orderService", new BeanDefinition(OrderService.class, propertyValues));

        OrderService orderService = (OrderService) beanFactory.getBean("orderService");
        orderService.itWorks();
    }
}
