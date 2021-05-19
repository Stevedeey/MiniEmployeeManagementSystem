package com.example.employeemanagement.repositories;

import com.example.employeemanagement.model.Leavee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leavee, Long> {
    Leavee findLeaveById(Long id);
    Leavee findLeaveByEmail(String email);


}
