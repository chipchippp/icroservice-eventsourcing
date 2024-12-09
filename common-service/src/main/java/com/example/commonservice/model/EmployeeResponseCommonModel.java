package com.example.commonservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseCommonModel {
    private String id;
    private String fullName;
    private String Kin;
    private Boolean isDisciplined;
}
