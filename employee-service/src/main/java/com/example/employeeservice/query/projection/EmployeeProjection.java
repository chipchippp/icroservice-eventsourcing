package com.example.employeeservice.query.projection;

import com.example.employeeservice.command.data.Employee;
import com.example.employeeservice.command.data.EmployeeRepository;
import com.example.employeeservice.query.model.EmployeeResponseModel;
import com.example.employeeservice.query.queries.GetAllEmployeeQuery;
import com.example.employeeservice.query.queries.GetEmployeeDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EmployeeProjection {
    private final EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query) {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseModel> result =  employees.stream().map(employee -> {
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, employeeResponseModel);
            return employeeResponseModel;
        }).toList();
        return result;
    }

    @QueryHandler
    public EmployeeResponseModel handle(GetEmployeeDetailQuery query) throws Exception {
        EmployeeResponseModel model = new EmployeeResponseModel();
        Employee employee = employeeRepository.findById(query.getId()).orElseThrow(() -> new Exception("Employee not found id: " + query.getId()));
        BeanUtils.copyProperties(employee, model);
        return model;
    }
}
