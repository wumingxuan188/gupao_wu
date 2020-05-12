package com.spring.demo.service.Impl;

import com.spring.annotation.MyService;
import com.spring.demo.service.IDemoService;
@MyService
public class DemoService implements IDemoService {
    @Override
    public String query(String name) {
        return "hello, i'm is"+ name;
    }
}
