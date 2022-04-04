package com.quasar.operation.quasarmessagesplitprocessor.domain.processor;

import com.quasar.operation.quasarmessagesplitprocessor.util.TestUtils;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ProcessorTest {
    private FixtureConfiguration<Processor> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Processor.class);
    }

    @Test
    public void initializeProcessorTest() {
        fixture.givenNoPriorActivity()
                .when(TestUtils.initializeProcessorCommand())
                .expectEvents(TestUtils.processorInitializedDomainEvent());
    }

    @Test
    public void createProcessorTraceCommandTest() {
        fixture.given(TestUtils.processorInitializedDomainEvent())
                .when(TestUtils.createProcessorTraceCommandSato())
                .expectEvents(TestUtils.traceReceivedDomainEventSato());
    }

    @Test
    public void processorIsNotPreparedTest() {
        fixture.given(
                        TestUtils.processorInitializedDomainEvent()
                        , TestUtils.traceReceivedDomainEventSato()
                        , TestUtils.traceReceivedDomainEventSato())
                .when(TestUtils.createProcessorTraceCommandSato())
                .expectEvents(TestUtils.traceReceivedDomainEventSato());

        fixture.given(
                        TestUtils.processorInitializedDomainEvent()
                        , TestUtils.traceReceivedDomainEventKenobi()
                        , TestUtils.traceReceivedDomainEventSato()
                ).when(TestUtils.createProcessorTraceCommandSato())
                .expectEvents(TestUtils.traceReceivedDomainEventSato());
    }

    @Test
    public void processorIsPreparedTest() {
        fixture.given(
                        TestUtils.processorInitializedDomainEvent()
                        , TestUtils.traceReceivedDomainEventKenobi()
                        , TestUtils.traceReceivedDomainEventSkywalker())
                .when(TestUtils.createProcessorTraceCommandSato())
                .expectEvents(TestUtils.traceReceivedDomainEventSato(), TestUtils.processorPreparedDomainEvent());
    }

    @Test
    public void processMessageTest() {
        fixture.given(
                        TestUtils.processorInitializedDomainEvent()
                        , TestUtils.traceReceivedDomainEventKenobi()
                        , TestUtils.traceReceivedDomainEventSkywalker()
                        , TestUtils.traceReceivedDomainEventSato()
                        , TestUtils.processorPreparedDomainEvent())
                .when(TestUtils.processMessageCommant())
                .expectEvents(TestUtils.messageProcessedDomainEvent());
    }
}
