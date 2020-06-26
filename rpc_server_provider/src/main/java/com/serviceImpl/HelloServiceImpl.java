package com.serviceImpl;

import com.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String data) {
        System.err.println("进入方法");
        return  "success : "+ data;
    }
}
