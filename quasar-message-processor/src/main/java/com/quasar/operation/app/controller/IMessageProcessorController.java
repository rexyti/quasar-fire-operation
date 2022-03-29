package com.quasar.operation.app.controller;

import com.quasar.operation.app.dto.MessageProcessorRequest;
import com.quasar.operation.app.dto.MessageProcessorResponse;

public interface IMessageProcessorController {
    public MessageProcessorResponse postTopSecret(MessageProcessorRequest request);
}
