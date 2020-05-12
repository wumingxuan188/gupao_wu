package com.decoration;

public class EggDecorator extends BattercakeDecorator {
    public EggDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "加鸡蛋";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 1;
    }

}
