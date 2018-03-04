package com.bby.axon;

import com.bby.axon.aggregates.MessagesAggregate;
import com.bby.axon.commands.CreateMessageCommand;
import com.bby.axon.commands.MarkReadMessageCommand;
import com.bby.axon.events.MessageCreatedEvent;
import com.bby.axon.events.MessageReadEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class MessagesAggregateIntegrationTest {

    private FixtureConfiguration<MessagesAggregate> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<MessagesAggregate>(MessagesAggregate.class);

    }

    @Test
    public void giveAggregateRoot_whenCreateMessageCommand_thenShouldProduceMessageCreatedEvent() {
        String eventText = "Hello, how is your day?";
        String id = UUID.randomUUID().toString();
        fixture.given()
                .when(new CreateMessageCommand(id, eventText))
                .expectEvents(new MessageCreatedEvent(id, eventText));
    }

    @Test
    public void givenMessageCreatedEvent_whenReadMessageCommand_thenShouldProduceMessageReadEvent() {
        String id = UUID.randomUUID().toString();

        fixture.given(new MessageCreatedEvent(id, "Hello :-)"))
                .when(new MarkReadMessageCommand(id))
                .expectEvents(new MessageReadEvent(id));
    }

}
