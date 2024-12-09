package com.example.borrowingservice.command.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingRequestModel {
    private String id;
    @NotBlank(message = "Book id is mandatory")
    private String bookId;

    @NotBlank(message = "Employee id is mandatory")
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
}