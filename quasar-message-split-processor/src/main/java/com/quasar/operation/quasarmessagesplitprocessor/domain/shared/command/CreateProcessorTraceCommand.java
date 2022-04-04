package com.quasar.operation.quasarmessagesplitprocessor.domain.shared.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CreateProcessorTraceCommand {
    @TargetAggregateIdentifier
    private String processorId;
    private String satelliteName;
    private Float distance;
    private List<String> message;
}
