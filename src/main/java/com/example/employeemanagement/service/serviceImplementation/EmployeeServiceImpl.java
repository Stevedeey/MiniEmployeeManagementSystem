package com.example.employeemanagement.service.serviceImplementation;

import com.example.employeemanagement.model.Admin;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repositories.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeService;
import com.example.employeemanagement.utility.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
   private EmployeeRepository employeeRepository;
  @Override
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {

      Employee user = employeeRepository.findEmployeeByEmail(employee.getEmail()); //to check if user doesnt exist
      if(user==null) {
          return  employeeRepository.save(employee); //new user saved
      }
      else  return null;
    }

  @Override
  public Employee editEmployee(Employee employee) {
    Employee user = employeeRepository.findEmployeeByEmail(employee.getEmail()); //to check if user doesnt exist
//    user.setEmail(employee.getEmail());
//    user.setAddress(employee.getAddress());
//    user.setPassword(employee.getPassword());
//    user.setFirstName(employee.getFirstName());
//    user.setLastName(employee.getLastName());
//    return employeeRepository.save(user);  one war
    if(user!=null) {
      return  employeeRepository.save(employee); //new user saved(another way)
    }
    else  return null;
  }

    @Override
    public Employee  getEmployeeById(Long id) {
      Optional<Employee> optional =  employeeRepository.findById(id);
      Employee employee = null;
      if(optional.isPresent()){
          employee = optional.get();
      }else {
          throw new RuntimeException("Employee not found found for-> "+id);

      }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
      employeeRepository.deleteById(id);
    }
  public Employee loginEmployee(String email, String password){

    Employee userData = null;

    try {

      userData = employeeRepository.findEmployeeByEmail(email);


      if(!password.equals(userData.getPassword())){
        userData = null;
      }


    } catch (Exception e) {
      e.printStackTrace();
    }

    return userData;
  }
}
