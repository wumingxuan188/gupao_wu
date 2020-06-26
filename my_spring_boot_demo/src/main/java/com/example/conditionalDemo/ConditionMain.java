package com.example.conditionalDemo;

import com.example.ExampleApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
 public class ConditionMain {
    public static void main(String[] args) {

        ApplicationContext context=new AnnotationConfigApplicationContext(ConditionalConfig.class);
        ConditionService bean = context.getBean(ConditionService.class);
        System.err.println("ConditionService:"+bean);
    }
}
