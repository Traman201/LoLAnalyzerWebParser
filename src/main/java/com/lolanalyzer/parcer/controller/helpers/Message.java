package com.lolanalyzer.parcer.controller.helpers;

/**
 * Описание сообщений
 */
public class Message {
    /**
     * Возможные типы сообщений
     * <p>
     *     <code>INFO</code> - сообщение содержит общую информацию
     *     <code>ERROR</code> - сообщение содержит текст ошибки
     *     <code>SUCCESS</code> - сообщение содержит текст описания успешно выполненной операции
     * </p>
     */
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
