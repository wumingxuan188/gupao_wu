package com.example.conditionalDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalConfig {

    @Conditional(MyConditional.class)
    @Bean
    public  ConditionService conditionService(){
        return  new ConditionService();
    }
}
