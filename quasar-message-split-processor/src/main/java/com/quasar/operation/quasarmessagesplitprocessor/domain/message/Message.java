package com.quasar.operation.quasarmessagesplitprocessor.domain.message;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command.CreateMessageCommand;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.event.MessageCreatedDomainEvent;
import lombok.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @AggregateIdentifier
    private String messageId;
    private String finalMessage;
    private MessagePosition position;

    @CommandHandler
    public Message (CreateMessageCommand createMessageCommand) {
        AggregateLifecycle.apply(new MessageCreatedDomainEvent(createMessageCommand.getMessageId()
                , createMessageCommand.getMessage()
                , createMessageCommand.getPosition()));
    }


    @EventSourcingHandler
    public void create(MessageCreatedDomainEvent messageCreatedDomainEvent) {
        this.messageId = messageCreatedDomainEvent.getMessageId();
        this.finalMessage = messageCreatedDomainEvent.getFinalMessage();
        this.position = messageCreatedDomainEvent.getPosition();
    }

}
