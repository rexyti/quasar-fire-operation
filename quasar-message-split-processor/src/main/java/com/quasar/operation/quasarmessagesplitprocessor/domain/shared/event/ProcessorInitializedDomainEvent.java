package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event;

import com.quasar.operation.quasarmessagesplitprocessor.domain.trace.Trace;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@AllArgsConstructor
public class ProcessorInitializedDomainEvent {
    @TargetAggregateIdentifier
    private String processorId;
    private List<String> satellitesNames;
}
