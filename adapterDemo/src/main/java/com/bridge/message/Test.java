package com.bridge.message;

public class Test {
    public static void main(String[] args) {
        MessageBridge urgent = new UrgentMessage(new EmailMessage());
        MessageBridge urgent1 = new UrgentMessage(new SmsMessage());
        urgent1.send("你好", "123");
        urgent.send("你好", "123");

        MessageBridge normalMessage = new NormalMessage(new SmsMessage());
        normalMessage.send("你好", "123");
    }

}
