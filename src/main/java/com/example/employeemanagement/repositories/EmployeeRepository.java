package com.example.employeemanagement.repositories;

import com.example.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This will create a DAO for us to easily perform CRUD operation
@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
    Employee findEmployeeByEmail(String email);


}
