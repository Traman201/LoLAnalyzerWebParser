package com.lolanalyzer.parcer.controller.helpers;

import antlr.debug.MessageAdapter;
import com.lolanalyzer.parcer.controller.GameController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Брокер сообщений для контроллера информации о матче
 *
 * <p>
 *     Используется для вывода сообщений на веб-страницу с информацией о матче
 * </p>
 */
@Service
public class GameControllerMessenger {


    ArrayList<Message> messages = new ArrayList<>();


    /**
     * Проверка на наличие сообщений
     * @return <code>true</code> если у брокера есть сообщения
     */
    public boolean hasMessages(){
        return !messages.isEmpty();
    }

    /**
     * Добавляет сообщение брокеру
     * @param text Текст сообщения для вывода
     * @param messageType Тип сообщения
     * @see com.lolanalyzer.parcer.controller.helpers.Message.MessageType
     */
    public void addMessage(String text, Message.MessageType messageType){
        messages.add(new Message(text, messageType));
    }

    /**
     * Возращает массив сообщений, хранящихся в брокере и очищает хранилище сообщений.
     *
     * @return Массив сообщений
     * @see com.lolanalyzer.parcer.controller.helpers.Message
     */
    public Message[] getMessages(){
        Message[] messagesArray = messages.toArray(new Message[0]);
        messages.clear();
        return messagesArray;

    }

}
