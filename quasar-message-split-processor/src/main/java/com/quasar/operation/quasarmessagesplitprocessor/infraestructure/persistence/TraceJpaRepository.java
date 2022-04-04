package com.quasar.operation.quasarmessagesplitprocessor.infraestructure.persistence;

import com.quasar.operation.quasarmessagesplitprocessor.domain.shared.entity.MessageTrace;
import com.quasar.operation.quasarmessagesplitprocessor.domain.trace.TraceRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TraceJpaRepository implements TraceRepository {

    private MessageTraceEntityRepository messageTraceEntityRepository;

    public TraceJpaRepository(MessageTraceEntityRepository messageTraceEntityRepository){
        this.messageTraceEntityRepository = messageTraceEntityRepository;
    }

    @Override
    public void createTrace(String traceId, String satelliteName, Float distance, List<String> message) {
        MessageTrace messageTrace = new MessageTrace();
        messageTrace.setUid(traceId);
        messageTrace.setSatelliteName(satelliteName);
        messageTrace.setDistance(distance);
        messageTrace.setCreationDate(new Date());
        messageTrace.setMessage(message.stream().collect(Collectors.joining(",", "[", "]")));
        messageTraceEntityRepository.save(messageTrace);
    }
}
