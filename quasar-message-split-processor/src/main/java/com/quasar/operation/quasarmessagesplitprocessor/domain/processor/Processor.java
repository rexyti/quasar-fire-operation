package com.quasar.operation.quasarmessagesplitprocessor.domain.processor;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.CreateProcessorTraceCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.InitializeProcessorCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.ProcessMessageCommant;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Aggregate
@Data
@NoArgsConstructor
public class Processor {
    @AggregateIdentifier
    private String processorId;
    private Map<String, ProcessorTrace> satellitesInfo;
    private ProcessorStatus status;

    public enum ProcessorStatus {
        WAITING,FAIL,SUCCESS
    }

    Logger logger = LoggerFactory.getLogger(Processor.class);

    @CommandHandler
    public Processor(InitializeProcessorCommand command){
        logger.info(command.toString());
        AggregateLifecycle.apply(
                new ProcessorInitializedDomainEvent(command.getProcessorId(), command.getSatellitesNames()));
    }

    @EventSourcingHandler
    public void initialize(ProcessorInitializedDomainEvent processorInitializedDomainEvent) {
        logger.info(processorInitializedDomainEvent.toString());
        HashMap<String,ProcessorTrace> satellites = new HashMap<>();
        processorInitializedDomainEvent.getSatellitesNames().forEach(
                (satellite)->satellites.put(satellite,null)
        );
        this.satellitesInfo = satellites;
        this.processorId = processorInitializedDomainEvent.getProcessorId();

    }

    @CommandHandler
    public void receiveTrace(CreateProcessorTraceCommand createProcessorTraceCommand){
        logger.info(createProcessorTraceCommand.toString());
        if (satellitesInfo.containsKey(createProcessorTraceCommand.getSatelliteName())){
            ProcessorTrace trace = new ProcessorTrace(
                    createProcessorTraceCommand.getSatelliteName()
                    , createProcessorTraceCommand.getDistance()
                    , createProcessorTraceCommand.getMessage());

            AggregateLifecycle.apply(new TraceReceivedDomainEvent(
                    createProcessorTraceCommand.getProcessorId()
                    , trace));
        }
    }

    @EventSourcingHandler
    public void addTrace(TraceReceivedDomainEvent traceReceivedDomainEvent){
        logger.info(traceReceivedDomainEvent.toString());
        this.status = ProcessorStatus.WAITING;
        String satelliteName = traceReceivedDomainEvent.getTrace().getSatelliteName();
        this.satellitesInfo.put(satelliteName,traceReceivedDomainEvent.getTrace());

        if (isReady()){
            logger.info("Processor is ready!!!");
            AggregateLifecycle.apply(new ProcessorPreparedDomainEvent(this.processorId,this.satellitesInfo));
        }
    }

    @CommandHandler
    public void processMessage(ProcessMessageCommant processMessageCommant){
        logger.info(processMessageCommant.toString());
        MessageProcessedDomainEvent messageProcessedDomainEvent=new MessageProcessedDomainEvent();
        BeanUtils.copyProperties(processMessageCommant,messageProcessedDomainEvent);
        AggregateLifecycle.apply(messageProcessedDomainEvent);
    }

    @EventSourcingHandler
    public void processMessage(MessageProcessedDomainEvent messageProcessedDomainEvent){
        logger.info(messageProcessedDomainEvent.toString());
        this.status = messageProcessedDomainEvent.getStatus();
        if(messageProcessedDomainEvent.getStatus().equals(ProcessorStatus.SUCCESS)){
            this.satellitesInfo.keySet().forEach(
                    (satellite)->satellitesInfo.put(satellite,null)
            );
        }
    }


    private boolean isReady(){
        return !this.satellitesInfo.entrySet().stream().anyMatch(entry -> !Optional.ofNullable(entry.getValue()).isPresent());
    }
}
