package com.strategy.pay;

public class WechatPay extends Payment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    public double queryBalance() {
        return 200;
    }
}
