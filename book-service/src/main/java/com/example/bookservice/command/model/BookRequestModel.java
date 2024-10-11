package com.example.bookservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestModel {
    private String id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name should have between 2 and 30 characters")
    private String name;
    @NotBlank(message = "Author is mandatory")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Author should have only letters")
    private String author;
    private Boolean isReady;
}
