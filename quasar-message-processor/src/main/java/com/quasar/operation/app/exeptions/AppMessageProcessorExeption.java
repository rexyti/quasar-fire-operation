package com.quasar.operation.app.exeptions;

public class AppMessageProcessorExeption extends RuntimeException{
    
    public AppMessageProcessorExeption(String errorMessage){
        super(errorMessage);
    }

}
