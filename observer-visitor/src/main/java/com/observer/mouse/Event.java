package com.observer.mouse;

import java.lang.reflect.Method;

public class Event {
    private  Object source;

    private  String trigger;

    private Method callback;

    private EventListener target;

    public Event(Method callback, EventListener target) {
        this.callback = callback;
        this.target = target;
    }

    public Object getSource() {
        return source;
    }

    public Event setSource(Object source) {
        this.source = source;
        return this;
    }

    public String getTrigger() {
        return trigger;
    }

    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Method getCallback() {
        return callback;
    }

    public Event setCallback(Method callback) {
        this.callback = callback;
        return this;
    }

    public EventListener getTarget() {
        return target;
    }

    public Event setTarget(EventListener target) {
        this.target = target;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source=" + source +
                ", trigger='" + trigger + '\'' +
                ", callback=" + callback +
                ", target=" + target +
                '}';
    }
}
