package com.example.borrowingservice.command.controller;

import com.example.borrowingservice.command.command.CreateBorrowingCommand;
import com.example.borrowingservice.command.command.DeleteBorrowingCommand;
import com.example.borrowingservice.command.command.UpdateBorrowingCommand;
import com.example.borrowingservice.command.model.BorrowingRequestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/borrowings")
public class BorrowingCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    public String createBook(@Valid @RequestBody BorrowingRequestModel model) {
        CreateBorrowingCommand command = new CreateBorrowingCommand(
                UUID.randomUUID().toString(),
                model.getBookId(),
                model.getEmployeeId(),
                new Date());
        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{id}")
    public String updateBook(@Valid @RequestBody BorrowingRequestModel model, @PathVariable String id) {
        UpdateBorrowingCommand command = new UpdateBorrowingCommand(
                id,
                model.getBookId(),
                model.getEmployeeId(),
                new Date(), // Assuming borrowingDate is required
                model.getReturnDate() // Use returnDate from the model
        );
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id) {
        DeleteBorrowingCommand command = new DeleteBorrowingCommand(id);
        return commandGateway.sendAndWait(command);
    }
}
