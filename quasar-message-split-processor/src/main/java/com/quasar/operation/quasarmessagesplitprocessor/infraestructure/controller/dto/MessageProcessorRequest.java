package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageProcessorRequest {
    private Float distance;
    private List<String> message;
}
