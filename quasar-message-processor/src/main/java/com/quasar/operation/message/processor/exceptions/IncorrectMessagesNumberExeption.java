package com.quasar.operation.message.processor.exceptions;

public class IncorrectMessagesNumberExeption extends MessageProcessorExeption{
    public IncorrectMessagesNumberExeption(){
        super("The number of messages does not match the number of satellites");
    }

}