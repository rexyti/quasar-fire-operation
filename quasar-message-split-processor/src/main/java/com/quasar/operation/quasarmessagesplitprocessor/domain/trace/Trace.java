package com.quasar.operation.quasarmessagesplitprocessor.domain.trace;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceCreatedDomainEvent;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.TraceRejectedDomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
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
    private TraceStatus status;

    public enum TraceStatus {
        IN_PROCESS, REJECTED, ACCEPTED
    }

    @EventSourcingHandler
    public void create(TraceCreatedDomainEvent tracedCreatedDomainEvent) {
        this.traceId = tracedCreatedDomainEvent.getTraceId();
        this.satelliteName = tracedCreatedDomainEvent.getSatelliteName();
        this.distance = tracedCreatedDomainEvent.getDistance();
        this.message = tracedCreatedDomainEvent.getMessage();
        this.status = TraceStatus.IN_PROCESS;
    }

    @EventSourcingHandler
    public void rejectTrace(TraceRejectedDomainEvent traceRejectedDomainEvent) {
        this.status = TraceStatus.REJECTED;
    }

    @EventSourcingHandler
    public void acceptTrace(TraceRejectedDomainEvent traceRejectedDomainEvent) {
        this.status = TraceStatus.ACCEPTED;
    }
}
