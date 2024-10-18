package com.example.employeeservice.query.controller;

import com.example.employeeservice.query.model.EmployeeResponseModel;
import com.example.employeeservice.query.queries.GetAllEmployeeQuery;
import com.example.employeeservice.query.queries.GetEmployeeDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    private final QueryGateway queryGateway;

    @GetMapping
    public List<EmployeeResponseModel> getAllEmployees() {
        GetAllEmployeeQuery query = new GetAllEmployeeQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getEmployeeById(@PathVariable String employeeId) {
        GetEmployeeDetailQuery query = new GetEmployeeDetailQuery(employeeId);
        return queryGateway.query(query, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
    }
}
