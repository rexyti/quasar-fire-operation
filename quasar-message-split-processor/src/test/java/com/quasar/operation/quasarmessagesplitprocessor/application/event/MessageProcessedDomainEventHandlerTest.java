package com.quasar.operation.quasarmessagesplitprocessor.application.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessageRepository;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.ProcessedMessage;
import com.quasar.operation.quasarmessagesplitprocessor.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class MessageProcessedDomainEventHandlerTest {

    @Autowired
    MessageProcessedDomainEventHandler messageProcessedDomainEventHandler;

    @Autowired
    MessageRepository messageRepository;

    @BeforeEach
    public void before(){
        UUID.randomUUID().toString();
        messageRepository.createMessage(UUID.randomUUID().toString(),UUID.randomUUID().toString(), null, "FAIL");
    }

    @Test
    public void handleMessageProcessedDomainEventSuccess(){
        messageProcessedDomainEventHandler.handle(TestUtils.messageProcessedDomainEvent());
        ProcessedMessage processedMessage= messageRepository.getLastMessage().get();
        assertEquals("this is the message",processedMessage.getMessage());
        assertEquals(1.111f,processedMessage.getX());
        assertEquals(2.222f,processedMessage.getY());
        assertEquals("SUCCESS",processedMessage.getStatus());
    }

}
