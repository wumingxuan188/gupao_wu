package com.gupao.memento;

import java.util.Stack;

public class DraftsBox {

    private static final Stack<ArticleMemento> stack=new Stack<>();

    public  void add(ArticleMemento memento){
        stack.push(memento);
    }

    public  ArticleMemento get(){
        return  stack.pop();
    }
}
