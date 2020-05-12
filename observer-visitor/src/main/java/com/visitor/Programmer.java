package com.visitor;

import java.util.Random;

public class Programmer extends  Employee {
    public Programmer(String name) {
        super(name);
    }

    protected void accept(IVisitor visitor) {
        visitor.visitor(this);
    }
    public int getCode(){
        return new Random().nextInt(500);
    }
}
