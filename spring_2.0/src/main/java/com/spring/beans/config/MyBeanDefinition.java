package com.spring.beans.config;

/**
 * 配置类
 */
public class MyBeanDefinition {
    private  String beanName;
    private  String className;

    public MyBeanDefinition(String beanName, String className) {
        this.beanName = beanName;
        this.className = className;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getClassName() {
        return className;
    }
}
