package com.quasar.operation.quasarmessagesplitprocessor.application.event;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.ProcessMessageCommant;
import com.quasar.operation.quasarmessagesplitprocessor.application.dto.ProcessedMessageDto;
import com.quasar.operation.quasarmessagesplitprocessor.application.service.MessageProcessorService;
import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessagePosition;
import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.Processor;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.ProcessorPreparedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.exeption.DomainExeption;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessorPreparedDomainEventHandler {

    Logger logger = LoggerFactory.getLogger(ProcessorPreparedDomainEventHandler.class);

    private MessageProcessorService messageProcessorService;
    private CommandGateway commandGateway;

    public ProcessorPreparedDomainEventHandler (MessageProcessorService messageProcessorService, CommandGateway commandGateway){
        this.messageProcessorService = messageProcessorService;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void handle(ProcessorPreparedDomainEvent event){
        logger.info(event.toString());
        ProcessedMessageDto processedMessage;
        try {
            processedMessage = messageProcessorService.processMessage(event.getSatellitesInfo());
            processedMessage.setStatus(Processor.ProcessorStatus.SUCCESS);

        }catch (DomainExeption e) {
            processedMessage = new ProcessedMessageDto();
            processedMessage.setMessage(e.getMessage());
            processedMessage.setStatus(Processor.ProcessorStatus.FAIL);
        }

        logger.info(processedMessage.toString());

        ProcessMessageCommant processMessageCommant = new ProcessMessageCommant();
        processMessageCommant.setProcessorId(event.getProcessorId());

        if(processedMessage.getStatus().equals(Processor.ProcessorStatus.SUCCESS)){
            MessagePosition messagePosition = new MessagePosition(processedMessage.getPosition().getX(), processedMessage.getPosition().getY());
            processMessageCommant.setMessage(processedMessage.getMessage());
            processMessageCommant.setPosition(messagePosition);
            processMessageCommant.setStatus(Processor.ProcessorStatus.SUCCESS);
        }else{
            processMessageCommant.setStatus(Processor.ProcessorStatus.FAIL);
        }

        commandGateway.send(processMessageCommant);
    }
}
