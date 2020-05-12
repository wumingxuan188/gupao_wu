package com.observer.jdk;

public class Test {

    public static void main(String[] args) {
        Question question = new Question("观察者", "观察者太糟糕啦");

        Gper gper =Gper.getInstance();
        Teacher tom = new Teacher("tom");
        Teacher jerry = new Teacher("jerry");

        gper.addObserver(tom);
        gper.addObserver(jerry);
        gper.publishQuestion(question);
    }
}
