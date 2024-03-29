package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event;

import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.ProcessorTrace;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;

@Data
@AllArgsConstructor
public class ProcessorPreparedDomainEvent {
    @TargetAggregateIdentifier
    private String processorId;
    private Map<String, ProcessorTrace> satellitesInfo;
}
