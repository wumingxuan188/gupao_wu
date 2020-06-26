package com.example;

import org.example.demo.MyCoreService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ExampleApplication.class, args);

        System.err.println(context.getBean(MyCoreService.class));
    }

}
