package com.quasar.operation.quasarmessagesplitprocessor.domain.processor;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.MessageCreatedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.MessageProcessedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.ProcessorInitializedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.ProcessorPreparedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceReceivedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.trace.Trace;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceAcceptedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceRejectedDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Aggregate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Processor {
    @AggregateIdentifier
    private String processorId;
    private Map<String, Trace> satellitesInfo;
    private ProcessorStatus status;

    public enum ProcessorStatus {
        WAITING,FAIL,SUCCESS
    }

    @EventSourcingHandler
    public void initialize(ProcessorInitializedDomainEvent processorInitializedDomainEvent) {
        HashMap<String,Trace> satellites = new HashMap<>();
        processorInitializedDomainEvent.getSatellitesNames().stream().forEach(
                (satellite)->satellites.put(satellite,null)
        );
        this.satellitesInfo = satellites;
        this.processorId = processorInitializedDomainEvent.getProcessorId();
    }

    @EventSourcingHandler
    public void receiveTrace(TraceReceivedDomainEvent traceReceivedDomainEvent){
        this.status = ProcessorStatus.WAITING;

        String satelliteName = traceReceivedDomainEvent.getTrace().getSatelliteName();
        if (this.satellitesInfo.containsKey(satelliteName)){
            this.satellitesInfo.put(satelliteName,traceReceivedDomainEvent.getTrace());
            AggregateLifecycle.apply(new ProcessorPreparedDomainEvent(this.processorId,this.satellitesInfo));
        } else {
            AggregateLifecycle.apply(new TraceRejectedDomainEvent(traceReceivedDomainEvent.getTrace().getTraceId()));
        }
    }

    @EventSourcingHandler
    public void processMessageStatus (MessageProcessedDomainEvent messageProcessedDomainEvent){
        this.status = messageProcessedDomainEvent.getStatus();
        if (this.status.equals(ProcessorStatus.SUCCESS)){
            this.getSatellitesInfo().entrySet().stream()
                    .map(entry -> entry.getValue())
                    .map(trace -> new TraceAcceptedDomainEvent(trace.getTraceId()))
                    .forEach(AggregateLifecycle::apply);
        }

        AggregateLifecycle.apply(new MessageCreatedDomainEvent(
                UUID.randomUUID().toString()
                ,messageProcessedDomainEvent.getMessage()
                ,messageProcessedDomainEvent.getPosition()));
    }

    public boolean isReady(){
        return !this.satellitesInfo.entrySet().stream().anyMatch(entry -> !Optional.ofNullable(entry.getValue()).isPresent());
    }
}
