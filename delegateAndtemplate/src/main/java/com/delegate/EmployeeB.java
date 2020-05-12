package com.delegate;

public class EmployeeB implements Employee {
   private  final String good = "开发";
    public void doSomeThing(String task) {
        System.err.println("我是员工B;我擅长"+good+";我正在做"+task);
    }
}
