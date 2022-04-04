package com.quasar.operation.quasarmessagesplitprocessor.application.event;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.CreateTraceCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceReceivedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.trace.TraceRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TraceReceivedDomainEventHandler {

    Logger logger = LoggerFactory.getLogger(TraceReceivedDomainEventHandler.class);

    private TraceRepository traceRepository;

    private CommandGateway commandGateway;

    public TraceReceivedDomainEventHandler(TraceRepository traceRepository, CommandGateway commandGateway){
        this.traceRepository = traceRepository;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void handle(TraceReceivedDomainEvent event) {
        logger.info(event.toString());
        String traceId = UUID.randomUUID().toString();

        traceRepository.createTrace(
                traceId
                , event.getTrace().getSatelliteName()
                , event.getTrace().getDistance()
                , event.getTrace().getMessage());

        CreateTraceCommand createTraceCommand = CreateTraceCommand.builder()
                .TraceId(traceId)
                .distance(event.getTrace().getDistance())
                .message(event.getTrace().getMessage())
                .satelliteName(event.getTrace().getSatelliteName())
                .build();

        commandGateway.send(createTraceCommand);

    }
}
