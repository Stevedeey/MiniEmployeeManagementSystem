package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Admin;
import com.example.employeemanagement.web.dto.AdminRegistrationDTO;


public interface AdminServices {
    Admin save(AdminRegistrationDTO adminRegistrationDTO);

}
