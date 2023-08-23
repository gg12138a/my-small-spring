# IOC

## Tip

- 所有的BeanFactory实现，都继承自超类`DefaultSingletonBeanRegistry`

## 基本的工作流程

1. 定义BeanDefinition
2. 将其传入BeanFactory
3. 将beanName传入BeanFactory，从中获取已创建的bean对象

## `BeanFactory`接口
主要功能： 传入`beanName`，获取对应的bean对象

## `SingletonBeanRegistry`接口

主要功能： 传入`beanName`，获取对应的<b>单例对象</b>（而非Definition）。

