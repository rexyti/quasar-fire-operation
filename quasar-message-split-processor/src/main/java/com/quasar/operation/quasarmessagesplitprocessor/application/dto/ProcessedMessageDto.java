package com.quasar.operation.quasarmessagesplitprocessor.application.dto;

import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.Processor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessedMessageDto {
    private ProcessedMessageDtoPosition position;
    private String message;
    private Processor.ProcessorStatus status;
}
