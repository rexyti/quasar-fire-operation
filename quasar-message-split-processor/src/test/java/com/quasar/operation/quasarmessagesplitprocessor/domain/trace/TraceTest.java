package com.quasar.operation.quasarmessagesplitprocessor.domain.trace;

import com.quasar.operation.quasarmessagesplitprocessor.util.TestUtils;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TraceTest {
    private FixtureConfiguration<Trace> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Trace.class);
    }

    @Test
    public void createTraceTest(){
        fixture.givenNoPriorActivity()
                .when(TestUtils.createTraceCommand())
                .expectEvents(TestUtils.tracedCreatedDomainEvent());
    }
}
