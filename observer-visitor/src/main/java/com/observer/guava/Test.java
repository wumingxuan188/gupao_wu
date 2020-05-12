package com.observer.guava;

import com.google.common.eventbus.EventBus;

public class Test {
    public static void main(String[] args) {
        EventBus bus = new EventBus();
        VoEvent event = new VoEvent();
        bus.register(event);
        bus.post("测试");
    }
}
