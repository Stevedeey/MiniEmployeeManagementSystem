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
            if(tempLeave ==null)
              return leaveRepository.save(leavee);
            else return null;
    }

    @Override
    public Leavee editLeave(Leavee leavee) {
        var tempLeave = leaveRepository.findLeaveByEmail(leavee.getEmail());
            if(tempLeave !=null) { //the leave obj won't be null for edit.
                return leaveRepository.save(leavee);
            }
            else return null;
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
    public void deleteLeaveById(Long id) {
        leaveRepository.deleteById(id);
    }



}
