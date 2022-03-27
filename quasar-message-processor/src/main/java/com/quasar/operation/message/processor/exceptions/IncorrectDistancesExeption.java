package com.quasar.operation.message.processor.exceptions;

public class IncorrectDistancesExeption extends MessageProcessorExeption{
    public IncorrectDistancesExeption(){
        super("The distances do not match with the calculated point");
    }

}