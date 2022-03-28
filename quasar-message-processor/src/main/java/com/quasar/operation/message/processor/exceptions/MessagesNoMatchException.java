package com.quasar.operation.message.processor.exceptions;

public class MessagesNoMatchException extends MessageProcessorExeption{
    
    public MessagesNoMatchException(){
        super("messages cannot be unified");
    }

}