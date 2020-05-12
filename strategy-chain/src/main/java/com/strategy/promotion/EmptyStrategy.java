package com.strategy.promotion;

public class EmptyStrategy implements IPromotionStrategy {
    public void doPromotion() {
        System.err.println("无优惠活动");
    }
}
