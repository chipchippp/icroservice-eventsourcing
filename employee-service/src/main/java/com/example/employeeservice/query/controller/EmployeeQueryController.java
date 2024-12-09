package com.example.employeeservice.query.controller;

import com.example.commonservice.model.EmployeeResponseCommonModel;
import com.example.commonservice.queries.GetDetailEmployeeQuery;
import com.example.commonservice.service.KafkaService;
import com.example.employeeservice.query.model.EmployeeResponseModel;
import com.example.employeeservice.query.queries.GetAllEmployeeQuery;
import com.example.employeeservice.query.queries.GetByIsDisciplined;
import com.example.employeeservice.query.queries.GetEmployeeDetailQuery;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Hidden
@Tag(name = "Employee Query", description = "Employee Query API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeQueryController {
    private final QueryGateway queryGateway;
    private final KafkaService kafkaService;

    @Operation(
            summary = "Get all employees",
            description = "Get all employees with filter",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "Successful operation"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            }
    )
    @GetMapping
    public List<EmployeeResponseModel> getAllEmployees() {
        GetAllEmployeeQuery query = new GetAllEmployeeQuery();
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @Operation(summary = "Get all employees" , description = "Get all employees with filter isDisciplined")
    @GetMapping("/disciplined")
    public List<EmployeeResponseModel> getByIsDisciplinedEmployees(@RequestParam(required = false, defaultValue = "false") Boolean isDisciplined) {
        return queryGateway.query(new GetByIsDisciplined(isDisciplined),
                ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join();
    }

    @Hidden
    @Operation(summary = "Get employee by id")
    @GetMapping("/{employeeId}")
    public EmployeeResponseCommonModel getDetailEmployee(@PathVariable String employeeId) {
        return queryGateway.query(new GetDetailEmployeeQuery(employeeId),ResponseTypes.instanceOf(EmployeeResponseCommonModel.class)).join();

    }
}
