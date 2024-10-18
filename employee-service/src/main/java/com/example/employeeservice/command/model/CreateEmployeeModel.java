package com.example.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeModel {
    private String id;
    @NotBlank(message = "FullName is mandatory")
    @Size(min = 2, max = 30, message = "FullName should have between 2 and 30 characters")
    private String fullName;
    @NotBlank(message = "Kin is mandatory")
    private String kin;
    private Boolean isDisciplined;

}
