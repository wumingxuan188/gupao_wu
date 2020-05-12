package com.strategy.pay;

public abstract class Payment {

    public abstract  String getName();

    public void pay(String uid,double mount){
        if(queryBalance()>mount){
            System.err.println("支付成功,消费:"+mount+"元");
        }else {
            System.err.println("余额不足,请更换支付方式");
        }

    }

    public abstract double queryBalance();
}
