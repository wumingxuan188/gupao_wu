package com.visitor;

import java.util.Random;

public class Manager extends  Employee {
    public Manager(String name) {
        super(name);
    }

    protected void accept(IVisitor visitor) {
        visitor.visitor(this);
    }
    public int getProtect(){
        return new Random().nextInt(10);
    }
}
