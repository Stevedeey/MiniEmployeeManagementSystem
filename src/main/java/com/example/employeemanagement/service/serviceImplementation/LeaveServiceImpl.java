package com.example.employeemanagement.service.serviceImplementation;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.Leavee;
import com.example.employeemanagement.repositories.LeaveRepository;
import com.example.employeemanagement.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

   @Autowired
   LeaveRepository leaveRepository;

    @Override
    public List<Leavee> getAllEmployees(){
        return leaveRepository.findAll();
    }

    @Override
    public Leavee saveLeave(Leavee leavee) {
   var tempLeave = leaveRepository.findLeaveByEmail(leavee.getEmail());
       // if (tempLeave == null){
        Leavee temp = null;
        try {
            temp = leaveRepository.save(leavee);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // }
       return temp;
    }


    @Override
    public Leavee getEmployeeById(Long id) {
        Optional<Leavee> optional =  leaveRepository.findById(id);
        Leavee leavee = null;
        if(optional.isPresent()){
            leavee = optional.get();
        }else {
            throw new RuntimeException("Employee not found found for-> "+id);

        }
        return leavee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        leaveRepository.deleteById(id);
    }



}
