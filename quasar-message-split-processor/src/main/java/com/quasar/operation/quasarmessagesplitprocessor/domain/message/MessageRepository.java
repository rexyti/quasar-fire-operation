package com.quasar.operation.quasarmessagesplitprocessor.domain.message;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.ProcessedMessage;

import java.util.Optional;

public interface MessageRepository {

    public void createMessage(String messageId,String finalMessage, MessagePosition position, String status);

    public Optional<ProcessedMessage> getLastMessage();
    
}
