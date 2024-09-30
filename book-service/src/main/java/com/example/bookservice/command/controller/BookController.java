package com.example.bookservice.command.controller;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.command.UpdateBookCommand;
import com.example.bookservice.command.model.BookRequestModel;
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
    public String createBook(@RequestBody BookRequestModel model) {
        CreateBookCommand createBookCommand = new CreateBookCommand(
                UUID.randomUUID().toString(),
                model.getName(),
                model.getAuthor(),
                true
        );
        return commandGateway.sendAndWait(createBookCommand);
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable String id, @RequestBody BookRequestModel model) {
        UpdateBookCommand createBookCommand = new UpdateBookCommand(
                id,
                model.getName(),
                model.getAuthor(),
                true
        );
        return commandGateway.sendAndWait(createBookCommand);
    }
}
