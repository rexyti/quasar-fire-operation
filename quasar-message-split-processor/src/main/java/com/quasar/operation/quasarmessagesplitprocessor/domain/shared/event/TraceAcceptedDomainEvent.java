package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class TraceAcceptedDomainEvent {
    @TargetAggregateIdentifier
    private String traceId;
}
