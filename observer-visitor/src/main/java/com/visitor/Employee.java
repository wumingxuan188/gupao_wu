package com.visitor;

import java.util.Random;

public abstract class Employee {
    private  String name;

    private int kpi;

    public String getName() {
        return name;
    }



    public int getKpi() {
        return kpi;
    }



    public Employee(String name) {
        this.kpi = new Random().nextInt(10);
        this.name=name;
    }

    protected abstract void  accept(IVisitor visitor);
}
