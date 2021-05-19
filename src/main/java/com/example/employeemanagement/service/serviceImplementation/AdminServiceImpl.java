package com.example.employeemanagement.service.serviceImplementation;

import com.example.employeemanagement.model.Admin;
import com.example.employeemanagement.model.Role;
import com.example.employeemanagement.repositories.AdminRepository;
import com.example.employeemanagement.service.AdminServices;
import com.example.employeemanagement.utility.Encryption;
import com.example.employeemanagement.web.dto.AdminRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminServices {
    @Autowired
   private AdminRepository adminRepository;



    @Override
    public Admin save(AdminRegistrationDTO adminRegistrationDTO) {
        Admin admin = new Admin(adminRegistrationDTO.getFirstname(),
                adminRegistrationDTO.getLastname(), adminRegistrationDTO.getEmail(),Encryption.encryptPassword(adminRegistrationDTO.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
       Admin currentUser =  adminRepository.findAdminByEmail(adminRegistrationDTO.getEmail());

       if(currentUser==null)
        return adminRepository.save(admin);
       else return null;
    }


    public  Admin getUser(String email, String password){

        Admin userData = null;

        try {

            userData = adminRepository.findAdminByEmail(email);


            if(!password.equals(Encryption.decryptPassword(userData.getPassword()))){
                userData = null;
            }
//            if(!password.equals(userData.getPassword())){
//                userData = null;
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userData;
    }



}
