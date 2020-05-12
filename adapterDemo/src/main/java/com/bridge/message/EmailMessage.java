package com.bridge.message;

public class EmailMessage implements IMessage {
    public void sendMessage(String msg, String user) {
        System.err.println("email发送 : " + msg + "to " + user);
    }
}
