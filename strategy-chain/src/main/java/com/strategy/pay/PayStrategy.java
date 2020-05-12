package com.strategy.pay;

import java.util.HashMap;
import java.util.Map;

public class PayStrategy {
    private static Map<String, Payment> data = new HashMap<>();

    static {
        data.put("Ali", new AliPay());
        data.put("Wechat", new WechatPay());
        data.put("UnionPay", new UnionPay());
    }

    public static Payment getInstance(String name) {
        return data.containsKey(name) ? data.get(name) : data.get("Ali");
    }
}
