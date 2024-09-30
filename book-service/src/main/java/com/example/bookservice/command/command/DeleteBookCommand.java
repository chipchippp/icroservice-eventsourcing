package com.example.bookservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String id;
}
