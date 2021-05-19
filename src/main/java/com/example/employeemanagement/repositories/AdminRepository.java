package com.example.employeemanagement.repositories;

import com.example.employeemanagement.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByEmail(String email);
}
