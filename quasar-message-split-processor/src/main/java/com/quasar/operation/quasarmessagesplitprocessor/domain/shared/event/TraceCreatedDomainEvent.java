package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@AllArgsConstructor
public class TraceCreatedDomainEvent {
    @TargetAggregateIdentifier
    private String traceId;
    private String satelliteName;
    private Float distance;
    private List<String> message;

}
