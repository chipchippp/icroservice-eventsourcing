package com.example.borrowingservice.command.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingDeletedEvent {
    private String id;
}
