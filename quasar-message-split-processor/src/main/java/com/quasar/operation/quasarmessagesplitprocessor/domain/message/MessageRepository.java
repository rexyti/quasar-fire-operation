package com.quasar.operation.quasarmessagesplitprocessor.domain.message;

public interface MessageRepository {

    public Message createMessage(String messageId,String finalMessage, MessagePosition position);

    public Message getLastMessage();
    
}
