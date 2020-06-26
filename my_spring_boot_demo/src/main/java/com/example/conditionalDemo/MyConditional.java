package com.example.conditionalDemo;

import org.example.demo.MyCoreService;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
      //  MyCoreService bean = conditionContext.getBeanFactory().getBean(MyCoreService.class);
      //  System.err.println(bean);

        return false;
    }
}
