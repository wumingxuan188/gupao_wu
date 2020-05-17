package com.bridge.message;

public abstract class MessageBridge {
    private IMessage iMessage;

    public MessageBridge(IMessage iMessage) {
        this.iMessage = iMessage;
    }

    public void send(String msg, String user) {
        iMessage.sendMessage(msg, user);
    }
}
