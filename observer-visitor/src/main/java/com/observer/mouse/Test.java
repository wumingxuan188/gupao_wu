package com.observer.mouse;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) {
        Mouse mouse = new Mouse();
        EventListener lisenter = new MouseEventLisenter();
        EventListener lisenter2 = new MouseEventLisenterTwo();

        mouse.addListen(MouseEventType.ON_CLICK, lisenter);
        mouse.addListen(MouseEventType.ON_CLICK, lisenter2);

        mouse.onClick();


    }
}
