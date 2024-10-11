package com.example.bookservice.command.controller;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.command.DeleteBookCommand;
import com.example.bookservice.command.command.UpdateBookCommand;
import com.example.bookservice.command.model.BookRequestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createBook(@Valid @RequestBody BookRequestModel model) {
        CreateBookCommand createBookCommand = new CreateBookCommand(
                UUID.randomUUID().toString(),
                model.getName(),
                model.getAuthor(),
                true
        );
        return commandGateway.sendAndWait(createBookCommand);
    }

    @PutMapping("/{bookId}")
    public String updateBook(@RequestBody BookRequestModel model, @PathVariable String bookId) {
        UpdateBookCommand createBookCommand = new UpdateBookCommand(
                bookId,
                model.getName(),
                model.getAuthor(),
                model.getIsReady()
        );
        return commandGateway.sendAndWait(createBookCommand);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        DeleteBookCommand command = new DeleteBookCommand(bookId);
        return commandGateway.sendAndWait(command);
    }
}
