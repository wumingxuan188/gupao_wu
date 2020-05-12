package com.observer.guava;

import com.google.common.eventbus.Subscribe;

public class VoEvent {

    @Subscribe
    public void subscribe(String pra){
        System.err.println("有变动:"+pra);
    }
}
