package com.quasar.operation.quasarmessagesplitprocessor.domain.trace;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.CreateTraceCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceCreatedDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Aggregate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trace {
    @AggregateIdentifier
    private String traceId;
    private String satelliteName;
    private Float distance;
    private List<String> message;

    @CommandHandler
    public Trace(CreateTraceCommand command) {
        AggregateLifecycle.apply(new TraceCreatedDomainEvent(
                traceId
                , command.getSatelliteName()
                , command.getDistance()
                , command.getMessage()));
    }

    @EventSourcingHandler
    public void create(TraceCreatedDomainEvent tracedCreatedDomainEvent) {
        this.traceId = tracedCreatedDomainEvent.getTraceId();
        this.satelliteName = tracedCreatedDomainEvent.getSatelliteName();
        this.distance = tracedCreatedDomainEvent.getDistance();
        this.message = tracedCreatedDomainEvent.getMessage();
    }


}
