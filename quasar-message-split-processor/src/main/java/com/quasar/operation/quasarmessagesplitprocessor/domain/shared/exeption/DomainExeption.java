package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.exeption;

public class DomainExeption extends RuntimeException{
    
    public DomainExeption(String errorMessage){
        super(errorMessage);
    }
    
}
