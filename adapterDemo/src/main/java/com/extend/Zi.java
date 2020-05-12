package com.extend;

public class Zi extends Fu {
    public Zi() {
        //super(100);
        this(100);
        System.err.println("子类无参");
    }

    public Zi(int i) {
        // super();
        System.err.println("子类有参");
    }
}

class Test {
    public static void main(String[] args) {
        Zi zi = new Zi();
    }
}
