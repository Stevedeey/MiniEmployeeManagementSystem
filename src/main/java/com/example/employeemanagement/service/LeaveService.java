package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Leavee;

import java.util.List;

public interface LeaveService {
    Leavee saveLeave(Leavee leavee);
    List<Leavee> getAllEmployees();
     Leavee getEmployeeById(Long id);
     void deleteEmployeeById(Long id);
}
