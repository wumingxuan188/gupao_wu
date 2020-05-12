package com.delegate;

public class Boss {
    public void allot(String task){
        new Leader().doSomeThing(task);
    }
}
