package com.quasar.operation.app.service;

import com.quasar.operation.app.dto.MessageProcessorRequest;
import com.quasar.operation.app.dto.MessageProcessorResponse;
import com.quasar.operation.message.processor.exceptions.MessageProcessorExeption;

public interface IMessageProcessorService {
    public MessageProcessorResponse processRequest(MessageProcessorRequest request) throws MessageProcessorExeption;
}
