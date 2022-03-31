package com.quasar.operation.app.controller.impl;

import com.quasar.operation.app.dto.MessageProcessorError;
import com.quasar.operation.app.exeptions.AppMessageProcessorExeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class MessageProcessorControllerExeptionHandler {
    @ExceptionHandler(value = { AppMessageProcessorExeption.class })
    public ResponseEntity<MessageProcessorError> handleAppMessageProcessorExeption(AppMessageProcessorExeption ex) {
        return new ResponseEntity<>(new MessageProcessorError(ex.getMessage()),HttpStatus.NOT_FOUND);
    }
}
