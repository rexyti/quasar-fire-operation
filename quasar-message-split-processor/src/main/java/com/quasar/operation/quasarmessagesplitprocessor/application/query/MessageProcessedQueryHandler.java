package com.quasar.operation.quasarmessagesplitprocessor.application.query;

import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessageRepository;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.ProcessedMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageProcessedQueryHandler {

    private MessageRepository messageRepository;

    public MessageProcessedQueryHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @QueryHandler
    public Optional<ProcessedMessage> handle (MessageProcessedQuery query){
        return messageRepository.getLastMessage();
    }

}
