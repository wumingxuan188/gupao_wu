package com.demo.service.Impl;

import com.demo.service.IDemoService;
import com.spring.annotation.MyService;

@MyService
public class DemoService implements IDemoService {
    @Override
    public String query(String name) {
        return "hello, i'm is "+ name;
    }
}
