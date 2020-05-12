package com.strategy.promotion;

public class CouponsStrategy implements IPromotionStrategy {
    public void doPromotion() {
        System.err.println("使用优惠券");
    }
}
