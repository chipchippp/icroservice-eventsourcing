package com.example.bookservice.command.aggregate;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.event.BookCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class BookAggregate {

//    liên kết id từ createBookCommand với id của book
    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand command) {
        BookCreatedEvent event = new BookCreatedEvent();
        BeanUtils.copyProperties(command, event);

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(BookCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }
}
