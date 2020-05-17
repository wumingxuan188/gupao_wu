package com.flyweight;

import java.util.Random;

public class Train implements Ticket {
    private String from;

    private String to;

    private Integer price;

    public Train(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void show(String type) {
        price = new Random().nextInt(100);

        System.err.println("出发地:" + from + "--->目的地:" + to + " 座位类型:" + type + ";票价:" + price);

    }
}
