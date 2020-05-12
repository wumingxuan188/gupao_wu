package com.spring.webmvc.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class MyHandlerMapping {
    private Pattern pattern;

    private Method method;

    private  Object instance;

    public MyHandlerMapping(Pattern pattern, Method method, Object instance) {
        this.pattern = pattern;
        this.method = method;
        this.instance = instance;
    }

    public MyHandlerMapping() {
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
