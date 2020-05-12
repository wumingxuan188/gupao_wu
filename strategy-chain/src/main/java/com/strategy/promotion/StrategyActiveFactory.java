package com.strategy.promotion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 策略工厂
 */
public class StrategyActiveFactory {
   private static Map<String,IPromotionStrategy> map=new HashMap<>();

   static {
      map.put(PromotionKey.CASHBACK,new CashbackStrategy());
      map.put(PromotionKey.COUPON,new CouponsStrategy());
      map.put(PromotionKey.GROUPBUY,new GroupStrategy());
   }

   private interface PromotionKey{
      String COUPON = "COUPON";
      String CASHBACK = "CASHBACK";
      String GROUPBUY = "GROUPBUY";
   }
   private static final IPromotionStrategy EMPTY=new EmptyStrategy();
   //获取策略
   public static  IPromotionStrategy getIPromotionStrategy(String name){
         if(!map.containsKey(name)){
            return EMPTY;
         }else {
          return   map.get(name);
         }
   }

   public  static Set<String> getPromotionKey(){
      Set<String> set = map.keySet();
      return set;
   }

}
