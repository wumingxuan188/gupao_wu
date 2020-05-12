package com.visitor;

public class CEOVisitor implements  IVisitor{
    public void visitor(Manager manager) {
        System.err.println("CEO����:"+manager.getName()+"�����kpi��"+manager.getKpi()+",����Ĳ�Ʒ����"+manager.getProtect());
    }

    public void visitor(Programmer programmer) {
        System.err.println("CEO����:"+programmer.getName()+"����Ա��kpi��"+programmer.getKpi());

    }
}
