package com.observer.mouse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventContext {

    private Map<String ,Event> events=new HashMap<>();

    public void addListen(String eventType , EventListener target, Method callback){
        events.put(eventType,new Event(callback,target));

    }
    public void addListen(String eventType , EventListener target){
        try {
            this.addListen(eventType,target,target.getClass().getMethod("on"+toFirstUpper(eventType),Event.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
    public String toFirstUpper(String name){
        char[] array = name.toCharArray();
        array[0] -=32;
        return  String.valueOf(array);
    }
        private void target(Event event){
            event.setSource(this);
            //event.setTime(System.currentTimeMillis());
            try {
                event.getCallback().invoke(event.getTarget(),event);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        protected void target(String key){
        if(!events.containsKey(key))return;
        this.target(events.get(key));
        }
}
