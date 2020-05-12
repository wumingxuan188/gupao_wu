package com.visitor;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private List<Employee> employees=new ArrayList<Employee>();

    public Report() {
        employees.add(new Programmer("����ԱA"));
        employees.add(new Programmer("����ԱB"));
        employees.add(new Programmer("����ԱC"));
        employees.add(new Manager("����A"));
        employees.add(new Manager("����B"));
        employees.add(new Manager("����C"));
    }
    public void showReport(IVisitor visitor){
        for (Employee employee : employees) {
            employee.accept(visitor);
        }
    }
}
