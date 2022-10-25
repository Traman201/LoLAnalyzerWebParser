package com.lolanalyzer.parcer.controller.helpers;

public class Message {
    public enum MessageType{
        INFO,
        ERROR,
        SUCCESS
    }

    public MessageType type;
    public String text;

    public Message(String text, MessageType type){
        this.text = text;
        this.type = type;
    }
}
