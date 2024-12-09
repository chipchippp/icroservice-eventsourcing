package com.example.commonservice.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseCommonModel {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
