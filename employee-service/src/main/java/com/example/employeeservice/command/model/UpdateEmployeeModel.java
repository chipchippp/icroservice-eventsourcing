package com.example.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeModel {
    @NotBlank(message = "Full name is mandatory")
    private String fullName;
    @NotBlank(message = "Kin is mandatory")
    private String Kin;

    @NotNull(message = "isDisciplined is mandatory")
    private Boolean isDisciplined;
}
