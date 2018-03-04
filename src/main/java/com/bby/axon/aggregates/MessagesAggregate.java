package com.bby.axon.aggregates;

import com.bby.axon.commands.CreateMessageCommand;
import com.bby.axon.commands.MarkReadMessageCommand;
import com.bby.axon.events.MessageCreatedEvent;
import com.bby.axon.events.MessageReadEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class MessagesAggregate {

    @AggregateIdentifier
    private String id;

    public MessagesAggregate() {
    }

    @CommandHandler
    public MessagesAggregate(CreateMessageCommand command) {
        apply(new MessageCreatedEvent(command.getId(), command.getText()));
    }

    @EventHandler
    public void on(MessageCreatedEvent event) {
        this.id = event.getId();
    }

    @CommandHandler
    public void markRead(MarkReadMessageCommand command) {
        apply(new MessageReadEvent(id));
    }

}
