package com.example.employeeservice.command.controller;

import com.example.employeeservice.command.command.CreateEmployeeCommand;
import com.example.employeeservice.command.command.DeleteEmployeeCommand;
import com.example.employeeservice.command.command.UpdateEmployeeCommand;
import com.example.employeeservice.command.model.CreateEmployeeModel;
import com.example.employeeservice.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createBook(@Valid @RequestBody CreateEmployeeModel model) {
        CreateEmployeeCommand createEmployeeCommand = new CreateEmployeeCommand(
                UUID.randomUUID().toString(),
                model.getFullName(),
                model.getKin(),
                false
        );
        return commandGateway.sendAndWait(createEmployeeCommand);
    }

    @PutMapping("/{employeeId}")
    public String updateBook(@Valid @RequestBody UpdateEmployeeModel model, @PathVariable String employeeId) {
        UpdateEmployeeCommand updateEmployeeCommand = new UpdateEmployeeCommand(
                employeeId,
                model.getFullName(),
                model.getKin(),
                model.getIsDisciplined()
        );
        return commandGateway.sendAndWait(updateEmployeeCommand);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteBook(@PathVariable String employeeId) {
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }

}
