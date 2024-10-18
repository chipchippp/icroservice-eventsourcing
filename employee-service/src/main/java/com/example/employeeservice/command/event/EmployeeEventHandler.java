package com.example.employeeservice.command.event;

import com.example.employeeservice.command.data.Employee;
import com.example.employeeservice.command.data.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EmployeeEventHandler {
    private final EmployeeRepository employeeRepository;

    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeUpdatedEvent event) {
        Optional<Employee> oldEmployee = employeeRepository.findById(event.getId());

        oldEmployee.ifPresent(employee -> {
            BeanUtils.copyProperties(event, employee);
            employeeRepository.save(employee);
        });
    }

    @EventHandler
    public void on(EmployeeDeletedEvent event) {
        Optional<Employee> oldEmployee = employeeRepository.findById(event.getId());

        oldEmployee.ifPresent(employee -> {
            employeeRepository.delete(employee);
        });
    }
}
