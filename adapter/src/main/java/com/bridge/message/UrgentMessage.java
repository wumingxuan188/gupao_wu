package com.bridge.message;

public class UrgentMessage extends MessageBridge {
    public UrgentMessage(IMessage iMessage) {
        super(iMessage);
    }

    @Override
    public void send(String msg, String user) {
        msg = "[加急]" + msg;
        super.send(msg, user);
    }
}
