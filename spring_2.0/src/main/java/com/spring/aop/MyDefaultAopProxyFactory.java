package com.spring.aop;

import com.spring.aop.support.MyAopSupport;

public class MyDefaultAopProxyFactory {
    public MyAopProxy createAopProxy(MyAopSupport config){
        Class targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0){
            return  new MyJdkDynamicAopProxy1(config);
        }
        return new MyCglibAopProxy();
    }
}
