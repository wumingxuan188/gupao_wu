package com.flyweight;

/**
 * 享元模式测试类
 */
public class Test {
    public static void main(String[] args) {
        Train train = new Train("北京", "聊城");
        train.show("硬座");
    }
}
