package com.example.employeeservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeCommand {
    @TargetAggregateIdentifier
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
}
