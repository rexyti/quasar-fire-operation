package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event;

import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessagePosition;
import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.Processor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageProcessedDomainEvent {

    @TargetAggregateIdentifier
    private String processorId;
    private String message;
    private MessagePosition position;
    private Processor.ProcessorStatus status;

}
