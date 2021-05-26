package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    Employee editEmployee(Employee employee);
}
