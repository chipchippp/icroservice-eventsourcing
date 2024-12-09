package com.example.borrowingservice.command.aggregate;

import com.example.borrowingservice.command.command.CreateBorrowingCommand;
import com.example.borrowingservice.command.command.DeleteBorrowingCommand;
import com.example.borrowingservice.command.command.UpdateBorrowingCommand;
import com.example.borrowingservice.command.event.BorrowingCreatedEvent;
import com.example.borrowingservice.command.event.BorrowingDeletedEvent;
import com.example.borrowingservice.command.event.BorrowingUpdatedEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@NoArgsConstructor
@Aggregate
public class BorrowingAggregate {

    @AggregateIdentifier
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;

    @CommandHandler
    public BorrowingAggregate(CreateBorrowingCommand command) {
        BorrowingCreatedEvent event = new BorrowingCreatedEvent();
        BeanUtils.copyProperties(command, event);

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateBorrowingCommand command) {
        BorrowingUpdatedEvent event = new BorrowingUpdatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(DeleteBorrowingCommand command) {
        BorrowingDeletedEvent event = new BorrowingDeletedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(BorrowingCreatedEvent event) {
        this.id = event.getId();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
    }

    @EventSourcingHandler
    public void on(BorrowingUpdatedEvent event){
        this.id = event.getId();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
        this.returnDate = event.getReturnDate();
    }

    @EventSourcingHandler
    public void on(BorrowingDeletedEvent event) {
        this.id = event.getId();
    }
}
