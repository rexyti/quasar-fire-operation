package com.quasar.operation.quasarmessagesplitprocessor.application.event;

import com.quasar.operation.quasarmessagesplitprocessor.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TraceReceivedDomainEventHandlerTest {
    @Autowired
    private TraceReceivedDomainEventHandler TraceReceivedDomainEventHandler;

    @Test
    public void handleProcessorPreparedDomainEventTestSuccessWithoutExeption(){
        TraceReceivedDomainEventHandler.handle(TestUtils.traceReceivedDomainEventSato());
    }
}
