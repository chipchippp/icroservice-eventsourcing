package com.example.bookservice.command.event;

import com.example.bookservice.command.data.Book;
import com.example.bookservice.command.data.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookEventHandler {
    private final BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event) {
        Optional<Book> oldBook = bookRepository.findById(event.getId());

        oldBook.ifPresent(book -> {
            BeanUtils.copyProperties(event, book);
            bookRepository.save(book);
        });
    }

    @EventHandler
    public void on(BookDeletedEvent event) {
        Optional<Book> oldBook = bookRepository.findById(event.getId());

        oldBook.ifPresent(book -> {
            bookRepository.delete(book);
        });
    }
}
