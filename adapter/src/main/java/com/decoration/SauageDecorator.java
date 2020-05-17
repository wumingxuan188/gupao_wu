package com.decoration;

public class SauageDecorator extends BattercakeDecorator {
    public SauageDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "加火腿肠";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
