package com.quasar.operation.message.processor.exceptions;

public class MessageProcessorExeption extends RuntimeException{
    
    public MessageProcessorExeption(String errorMessage){
        super(errorMessage);
    }

}
