package com.quasar.operation.quasarmessagesplitprocessor.domain.message;

import com.quasar.operation.quasarmessagesplitprocessor.util.TestUtils;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {
    private FixtureConfiguration<Message> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(Message.class);
    }

    @Test
    public void createMessageTest(){
        fixture.givenNoPriorActivity()
                .when(TestUtils.createMessageCommand())
                .expectEvents(TestUtils.messageCreatedDomainEvent());
    }
}
