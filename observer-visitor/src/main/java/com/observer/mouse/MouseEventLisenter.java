package com.observer.mouse;

public class MouseEventLisenter implements EventListener {
    public void  onClick(Event event){
        System.err.println("出发了单机");
    }
}
