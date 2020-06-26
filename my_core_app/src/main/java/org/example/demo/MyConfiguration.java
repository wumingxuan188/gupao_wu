package org.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {


    @Bean
    public MyCoreService myCoreService(){
        return  new MyCoreService();
    }
}
