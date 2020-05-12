package com.strategy.promotion;

import java.util.Iterator;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        IPromotionStrategy strategy = new EmptyStrategy();
        StrategyActive active = new StrategyActive(strategy);
        active.execute();

        Set<String> key = StrategyActiveFactory.getPromotionKey();
        Iterator<String> iterator = key.iterator();


        for (String s : key) {
            System.err.println(s);
        }

        IPromotionStrategy strategy1 = StrategyActiveFactory.getIPromotionStrategy("GROUPBUY");
        strategy1.doPromotion();
    }
}
