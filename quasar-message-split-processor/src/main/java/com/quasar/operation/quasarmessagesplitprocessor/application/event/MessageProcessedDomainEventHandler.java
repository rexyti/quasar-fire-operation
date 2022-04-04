package com.quasar.operation.quasarmessagesplitprocessor.application.event;

import com.quasar.operation.quasarmessagesplitprocessor.config.QuasarSplitProcessorConfig;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.CreateMessageCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessageRepository;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.InitializeProcessorCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.MessageProcessedDomainEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageProcessedDomainEventHandler {

    Logger logger = LoggerFactory.getLogger(MessageProcessedDomainEventHandler.class);

    private QuasarSplitProcessorConfig config;

    private MessageRepository messageRepository;

    private CommandGateway commandGateway;

    public MessageProcessedDomainEventHandler(MessageRepository messageRepository, CommandGateway commandGateway, QuasarSplitProcessorConfig config){
        this.messageRepository = messageRepository;
        this.commandGateway = commandGateway;
        this.config = config;
    }

    @EventHandler
    public void handle(MessageProcessedDomainEvent event){
        logger.info(event.toString());
        String messageId = UUID.randomUUID().toString();

        messageRepository.createMessage(messageId, event.getMessage(), event.getPosition(), event.getStatus().toString());

        CreateMessageCommand createMessageCommand = new CreateMessageCommand();
        createMessageCommand.setMessageId(messageId);
        createMessageCommand.setMessage(event.getMessage());
        createMessageCommand.setPosition(event.getPosition());
        createMessageCommand.setStatus(event.getStatus());

        config.setProcessorName(UUID.randomUUID().toString());
        InitializeProcessorCommand command = new InitializeProcessorCommand(config.getProcessorName(), config.getSatellitesNames());
        commandGateway.send(command);

        commandGateway.send(createMessageCommand);
    }

}
