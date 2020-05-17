package com.bridge.message;

public class NormalMessage extends MessageBridge {
    public NormalMessage(IMessage iMessage) {
        super(iMessage);
    }

    @Override
    public void send(String msg, String user) {
        super.send(msg, user);
    }
}
