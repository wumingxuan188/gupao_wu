package com.strategy.pay;

public class Test {
    public static void main(String[] args) {
        Order order = new Order("111", 255, "商品");
        order.pay("Wechat");
    }
}
