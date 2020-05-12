package com.observer.jdk;

import java.util.Observable;

public class Gper extends Observable {

    public String name="咕泡社区";

    private static Gper gper=new Gper();

    private Gper() {

    }
    public static  Gper getInstance(){
        return  gper;
    }

    public void publishQuestion(Question question){
        System.err.println(question);
        setChanged();
        notifyObservers(question);
    }
}
