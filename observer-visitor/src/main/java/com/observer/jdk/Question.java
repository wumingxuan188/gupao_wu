package com.observer.jdk;

public class Question {
    private String name;

    private  String context;

    public Question(String name, String context) {
        this.name = name;
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", context='" + context + '\'' +
                '}';
    }
}
