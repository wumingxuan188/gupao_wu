package com.decoration;

public class Test {
    public static void main(String[] args) {
        Battercake battercake;
        battercake = new BaseBattercake();
        battercake = new EggDecorator(battercake);
        battercake = new SauageDecorator(battercake);
        System.err.println("类型 : " + battercake.getMsg());
        System.err.println("价格:" + battercake.getPrice());
    }
}
