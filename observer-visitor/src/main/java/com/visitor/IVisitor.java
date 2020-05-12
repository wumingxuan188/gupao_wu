package com.visitor;

public interface IVisitor {
    void visitor(Manager manager);

    void visitor(Programmer programmer);
}
