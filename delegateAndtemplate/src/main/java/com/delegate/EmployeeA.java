package com.delegate;

public class EmployeeA implements Employee {
   private  final String good = "平面设计";
    public void doSomeThing(String task) {
        System.err.println("我是员工A;我擅长"+good+";我正在做"+task);
    }
}
