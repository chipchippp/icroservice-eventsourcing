package com.example.bookservice.command.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDeletedEvent {
    private String id;
}
