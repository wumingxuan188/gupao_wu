package com.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrainFactory {
    private final Map<String, Train> map = new ConcurrentHashMap<String, Train>();

    public void getInfo(String type) {

    }
}
