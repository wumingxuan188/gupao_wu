package com.strategy.pay;

public class AliPay extends Payment {
    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    public double queryBalance() {
        return 900D;
    }
}
