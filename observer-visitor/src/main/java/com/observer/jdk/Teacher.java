package com.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class Teacher implements Observer {
  public  String name;

    public Teacher(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
            Gper g= (Gper) o;
       // System.err.println(g.name);
        Question q= (Question) arg;
        System.err.println("老师："+name+","+q);
    }
}
