package com.decoration;

public abstract class BattercakeDecorator implements Battercake {
    private Battercake battercake;


    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }

    public String getMsg() {
        return this.battercake.getMsg();
    }

    public int getPrice() {
        return this.battercake.getPrice();
    }
}
