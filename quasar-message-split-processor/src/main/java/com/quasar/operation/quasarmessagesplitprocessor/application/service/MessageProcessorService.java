package com.quasar.operation.quasarmessagesplitprocessor.application.service;

import com.quasar.operation.quasarmessagesplitprocessor.application.dto.ProcessedMessageDto;
import com.quasar.operation.quasarmessagesplitprocessor.domain.processor.ProcessorTrace;
import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.exeption.DomainExeption;

import java.util.Map;

public interface MessageProcessorService {
    public ProcessedMessageDto processMessage(Map<String, ProcessorTrace> satellitesInfo) throws DomainExeption;
}
