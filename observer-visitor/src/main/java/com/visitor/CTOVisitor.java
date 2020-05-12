package com.visitor;

public class CTOVisitor implements  IVisitor{
    public void visitor(Manager manager) {
        System.err.println("CTO����:"+manager.getName()+"����Ĳ�Ʒ����"+manager.getProtect());
    }

    public void visitor(Programmer programmer) {
        System.err.println("CTO����:"+programmer.getName()+"����Ա�Ĵ�������"+programmer.getCode());

    }
}
