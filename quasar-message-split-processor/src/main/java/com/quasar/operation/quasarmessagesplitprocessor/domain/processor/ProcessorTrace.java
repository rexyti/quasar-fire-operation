package com.quasar.operation.quasarmessagesplitprocessor.domain.processor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessorTrace {
    private String satelliteName;
    private Float distance;
    private List<String> message;
}
