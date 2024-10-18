package com.example.employeeservice.query.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel {
    private String id;
    private String fullName;
    private String kin;
    private Boolean isDisciplined;
}
