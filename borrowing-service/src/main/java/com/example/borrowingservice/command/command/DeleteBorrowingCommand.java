package com.example.borrowingservice.command.command;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteBorrowingCommand {
    @TargetAggregateIdentifier
    private String id;
}
