package com.example.bookservice.command.controller;

import com.example.bookservice.command.command.CreateBookCommand;
import com.example.bookservice.command.model.BookRequestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
