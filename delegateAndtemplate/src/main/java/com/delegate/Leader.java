package com.delegate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Leader implements Employee {

    private static Map<String,Employee> map=new ConcurrentHashMap<String, Employee>();

    static {
        map.put("开发", new EmployeeB() );
        map.put("平面设计", new EmployeeA() );
    }

    public void doSomeThing(String task) {
           /* if ("开发".equals(task)){
                new EmployeeB().doSomeThing(task);
            }else if("平面设计".equals(task)){
                new EmployeeA().doSomeThing(task);
            }else {
                System.err.println("此任务员工都不擅长");
            }*/
           if(!map.containsKey(task)) {
               System.err.println("此任务员工都不擅长");
        return;
    }
           map.get(task).doSomeThing(task);
    }
}
