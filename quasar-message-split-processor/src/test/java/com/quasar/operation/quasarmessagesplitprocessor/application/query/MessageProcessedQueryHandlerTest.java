package com.quasar.operation.quasarmessagesplitprocessor.application.query;

import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessageRepository;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.ProcessedMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageProcessedQueryHandlerTest {

    @Autowired
    MessageProcessedQueryHandler messageProcessedQueryHandler;

    @Autowired
    MessageRepository messageRepository;


    @Test
    public void handleMessageProcessedQueryTest (){
        String uuid = UUID.randomUUID().toString();
        messageRepository.createMessage(uuid,"this message", null, "The status");

        ProcessedMessage processedMessage = messageProcessedQueryHandler.handle(new MessageProcessedQuery()).get();
        assertEquals(uuid,processedMessage.getUid());
        assertEquals("this message",processedMessage.getMessage());
        assertEquals("The status",processedMessage.getStatus());
    }
}
