package com.bridge.message;

public class SmsMessage implements IMessage {
    public void sendMessage(String msg, String user) {
        System.err.println("短信发送 : " + msg + "to" + user);
    }
}
