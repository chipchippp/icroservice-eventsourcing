package com.example.borrowingservice.query.model;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingResponseModel {
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
}
