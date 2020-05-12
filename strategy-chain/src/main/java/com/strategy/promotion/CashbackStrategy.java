package com.strategy.promotion;

public class CashbackStrategy implements IPromotionStrategy {
    public void doPromotion() {
        System.err.println("返现优惠活动");
    }
}
