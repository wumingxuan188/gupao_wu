package com.observer.mouse;

public class MouseEventLisenterTwo implements EventListener {
    public void  onClick(Event event){
        System.err.println("观察者2:触发了单击事件");
    }
}
