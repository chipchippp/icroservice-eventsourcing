package com.example.borrowingservice.command.event;

import com.example.borrowingservice.command.data.Borrowing;
import com.example.borrowingservice.command.data.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BorrowingEventHandler {
    private final BorrowingRepository borrowingRepository;

    @EventHandler
    public void on(BorrowingCreatedEvent event) {
        Borrowing borrowing = new Borrowing();
        BeanUtils.copyProperties(event, borrowing);
        borrowingRepository.save(borrowing);
    }

    @EventHandler
    public void on(BorrowingUpdatedEvent event) {
        Optional<Borrowing> oldBorrowing = borrowingRepository.findById(event.getId());

        oldBorrowing.ifPresent(borrowing -> {
            BeanUtils.copyProperties(event, borrowing);
            borrowingRepository.save(borrowing);
        });
    }

    @EventHandler
    public void on(BorrowingDeletedEvent event) {
        Optional<Borrowing> oldBorrowing = borrowingRepository.findById(event.getId());

        oldBorrowing.ifPresent(borrowing -> {
            borrowingRepository.delete(borrowing);
        });
    }
}
