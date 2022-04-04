package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event;

import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.ProcessorTrace;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class TraceReceivedDomainEvent {
    @TargetAggregateIdentifier
    private String processorId;
    private ProcessorTrace trace;
}
