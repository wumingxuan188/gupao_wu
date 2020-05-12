package com.observer.mouse;

public class Mouse extends  EventContext {
    public void onClick(){
        System.err.println("单击事件");
        this.target(MouseEventType.ON_CLICK);
    }
}
