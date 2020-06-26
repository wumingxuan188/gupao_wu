package com.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionTemplate bean = context.getBean(SqlSessionTemplate.class);
        bean.test();
    }
}
