package com.example.
        employeemanagement.controller;

import com.example.employeemanagement.dto.Login;
import com.example.employeemanagement.model.Admin;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;

import com.example.employeemanagement.service.serviceImplementation.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //Create A method handler for the homepage (index.html) to display a list of employee
//    @GetMapping("/emp_list")
//    public String viewHomePage(Model model){
//        model.addAttribute(
//
//        "listOfEmployees", employeeService.getAllEmployees());
//        return "emp_list";
//    }
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
    @RequestMapping(value = "/admin_dashboard", method = RequestMethod.GET)
    public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {

        HttpSession httpSession = request.getSession();
        Admin user = (Admin) httpSession.getAttribute("user");



        if(user == null) {
            ModelAndView mav = new ModelAndView("index");
            mav.addObject("person", new Admin());
            mav.addObject("login", new Login());
            httpSession.setAttribute("message", "You need to login first!!!");
            return mav;
        }

        ModelAndView mav = new ModelAndView("admin_dashboard");


        List<Employee> listOfEmployees = employeeService.getAllEmployees();

        mav.addObject("user", user);
        mav.addObject("listOfEmployees", listOfEmployees);

        return mav;
    }

    @GetMapping("/emp_dashboard")
    public ModelAndView emp_dashboard(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Employee user  = (Employee) session.getAttribute("user");
        if(user==null){
            ModelAndView mav = new ModelAndView("emp_login");
            mav.addObject("login", new Login());
            mav.addObject("user", new Employee());
            session.setAttribute("message","You need to login first");
            return mav;
        }
        ModelAndView mav = new ModelAndView("emp_dashboard");
        mav.addObject("user",user);
        return mav;
    }

    @GetMapping("/show_new_employee_form")
    public String show_new_employee_form(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "new_employee";

    }

    @PostMapping("/saveNewEmployee")
    public String saveNewEmployee(@ModelAttribute("employee") Employee employee){
       // @ModelAttribute annotation is added so that we may be able to bind form data to the Model
      //Now save employee to the database
       if(employeeService.saveEmployee(employee)!=null) {
           return "redirect:/admin_dashboard?success";
       }
       return "redirect:/admin_dashboard?error";
       }

       //
       @PostMapping("/saveEditedEmployee")
       public String saveEditedEmployee(@ModelAttribute("employee") Employee employee){
           // @ModelAttribute annotation is added so that we may be able to bind form data to the Model
           //Now save employee to the database
           System.out.println();
           if(employeeService.editEmployee(employee)!=null) {
               return "redirect:/admin_dashboard?success_update";
           }
           return "redirect:/admin_dashboard?error";
       }

    @GetMapping("/show_update_form/{id}")
    public  String show_update_form(@PathVariable(value = "id") long id, Model model){
       //getting employee by id from the service
        Employee employee = employeeService.getEmployeeById(id);

        //set employee as a model to prepopulate the form
        model.addAttribute("employee",employee);
        return "update_employee";

    }
    @GetMapping("show_delete_form/{id}")
    public String show_delete_form(@PathVariable(value = "id")long id){
        //call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/admin_dashboard";

    }

    @RequestMapping(value = "/emp_login", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.removeAttribute("message");

        ModelAndView mav = new ModelAndView("emp_login");
        mav.addObject("employee", new Employee());
        mav.addObject("emplogin", new Login());
        System.out.println("I got in the default controller "+mav);

        return mav;
    }



    @RequestMapping(value = "/emploginProcess", method = RequestMethod.POST)
    public String loginProcess(HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("emplogin") Login emplogin) {

        Employee user = employeeServiceImpl.loginEmployee(emplogin.getEmail(), emplogin.getPassword());

        HttpSession httpSession = request.getSession();


        if (user != null) {
            httpSession.setAttribute("user", user);


            return "/emp_dashboard";
        } else {
            httpSession.setAttribute("message", "Email or Password is wrong!!!");

            return "redirect:/";
        }
    }


    @RequestMapping(value = "/empprocessLogout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
