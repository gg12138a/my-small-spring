package org.example.test.factory;

import cn.hutool.core.io.IoUtil;
import org.example.beans.factory.support.DefaultListableBeanFactory;
import org.example.beans.factory.support.XmlBeanDefinitionReader;
import org.example.core.io.DefaultResourceLoader;
import org.example.core.io.Resource;
import org.example.test.bean.OrderService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BeanDefinitionLoaderTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void readFileFromClasspath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:test.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void readFileFromFileSystem() throws IOException {
        Resource resource = resourceLoader.getResource("src/../test.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void readFileFromUrl() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/gg12138a/my-small-spring/tree/master/src/test.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void readBeanDefFromXml(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        OrderService orderService = (OrderService) beanFactory.getBean("orderService");
        orderService.itWorks();
    }
}
