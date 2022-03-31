package com.quasar.operation.app.controller;

import com.quasar.operation.app.dto.MessageProcessorRequest;
import com.quasar.operation.app.dto.MessageProcessorResponse;

import org.springframework.http.ResponseEntity;

public interface IMessageProcessorController {
    public ResponseEntity<MessageProcessorResponse> postTopSecret(MessageProcessorRequest request);
}
