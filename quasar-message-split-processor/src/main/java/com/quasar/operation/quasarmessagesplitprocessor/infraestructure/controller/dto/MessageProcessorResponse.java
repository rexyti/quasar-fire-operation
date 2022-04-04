package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.controller.dto;

import com.quasar.operation.quasarmessagesplitprocessor.application.dto.ProcessedMessageDtoPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageProcessorResponse implements Serializable {
    private static final long serialVersionUID = 2710018868246114503L;
    private ProcessedMessageDtoPosition position;
    private String message;
}
