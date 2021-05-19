package com.example.employeemanagement.controller;

import com.example.employeemanagement.service.AdminServices;
import com.example.employeemanagement.web.dto.AdminRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/registration")
public class AdminRegistrationController {
    private AdminServices adminServices;




    public AdminRegistrationController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    //This below is important
    //The user object is stored here each time the form is posted
    //This makes the object become available on the controller
    // And if we aren't using this approach to get our object we can
    //use the one I commented below
    @ModelAttribute("admin")
    public AdminRegistrationDTO adminRegistrationDTO(){
        return new AdminRegistrationDTO();
    }

    /**
     *   @GetMapping
     *     public String showRegistration(Model model){
     *     model.addAttribute("user", new adminRegistrationDto());
     *         return "registration";
     *     }
     * @return
     */
    @GetMapping
    public String showRegistration(){
        return "registration";
    }

    @PostMapping
     public String registerAdminAccount(@ModelAttribute("admin") AdminRegistrationDTO adminRegistrationDTO){

       if(adminServices.save(adminRegistrationDTO)!=null)
       {
           return "redirect:/registration?success";
       }
      return "redirect:/registration?error";

     }
}
