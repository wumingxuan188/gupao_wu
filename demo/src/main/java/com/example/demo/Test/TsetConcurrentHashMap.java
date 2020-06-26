package com.example.demo.Test;

import java.util.concurrent.ConcurrentHashMap;

public class TsetConcurrentHashMap {
    private  static ConcurrentHashMap<String,Object> chm=new ConcurrentHashMap();
    public static void main(String[] args) {
        chm.put("test","success");
    }
}
