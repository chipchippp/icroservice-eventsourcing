package com.example.commonservice.event;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRollBackStatusEvent {
    private String bookId;
    private Boolean isReady;
    private String employeeId;
    private String borrowingId;
}
