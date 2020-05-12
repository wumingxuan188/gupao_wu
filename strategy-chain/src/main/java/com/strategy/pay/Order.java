package com.strategy.pay;

public class Order {

    private String id;
    private  double price;
    private  String name;

    public Order(String id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }
    public void pay(){
        pay("Ali");
    }

    public void  pay(String name){
           Payment payment = PayStrategy.getInstance(name);
        payment.pay(id,price);
    String name1 = payment.getName();
        System.err.println(name1);
}



}
