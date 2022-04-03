package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event;

import com.quasar.operation.quasarmessagesplitprocessor.domain.message.MessagePosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Data
@AllArgsConstructor
public class MessageCreatedDomainEvent {
    @TargetAggregateIdentifier
    private String messageId;
    private String finalMessage;
    private MessagePosition position;
}
