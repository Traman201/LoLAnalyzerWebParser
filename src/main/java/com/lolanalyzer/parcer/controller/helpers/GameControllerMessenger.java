package com.lolanalyzer.parcer.controller.helpers;

import antlr.debug.MessageAdapter;
import com.lolanalyzer.parcer.controller.GameController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;

@Service
public class GameControllerMessenger {


    ArrayList<Message> messages = new ArrayList<>();


    public boolean hasMessages(){
        return !messages.isEmpty();
    }

    public void addMessage(String text, Message.MessageType messageType){
        messages.add(new Message(text, messageType));
    }

    public Message[] getMessages(){
        Message[] messagesArray = messages.toArray(new Message[0]);
        messages.clear();
        return messagesArray;

    }

}
