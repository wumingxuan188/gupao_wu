package com.proxy;

import com.service.HelloService;

import java.lang.reflect.Proxy;

public class HelloProxy {
    private String ip;
    private int port;

    public HelloProxy(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(HelloService.class.getClassLoader(), new Class[]{HelloService.class}, new HelloInvocationHandler(ip, port));
    }
}
