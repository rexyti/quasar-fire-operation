package com.quasar.operation.app.controller.impl;

import com.quasar.operation.app.controller.IMessageProcessorController;
import com.quasar.operation.app.dto.MessageProcessorRequest;
import com.quasar.operation.app.dto.MessageProcessorResponse;
import com.quasar.operation.app.service.IMessageProcessorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topsecret")
public class MessageProcessorControllerImpl implements IMessageProcessorController {

    private IMessageProcessorService messageProcessor;

    public MessageProcessorControllerImpl(IMessageProcessorService messageProcessor){
        this.messageProcessor = messageProcessor;
    }

    @PostMapping(value ="/", produces = "application/json")
    @Override
    public ResponseEntity<MessageProcessorResponse> postTopSecret(@RequestBody MessageProcessorRequest request) {
        return new ResponseEntity<>(messageProcessor.processRequest(request), HttpStatus.OK);
    }
}
