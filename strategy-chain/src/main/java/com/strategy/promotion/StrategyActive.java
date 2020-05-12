package com.strategy.promotion;

/**
 * 策略的上下文
 */
public class StrategyActive {
    private    IPromotionStrategy iPromotionStrategy;

    public StrategyActive(IPromotionStrategy iPromotionStrategy) {
        this.iPromotionStrategy = iPromotionStrategy;
    }
    public   void execute(){
        iPromotionStrategy.doPromotion();
    }
}
