package com.service;

import com.proxy.HelloProxy;

public class Client {
    public static void main(String[] args) {
        HelloService helloService=new HelloProxy("localhost",8080).getProxy();
        String data = helloService.sayHello("hello");
        System.err.println(data);
    }
}
