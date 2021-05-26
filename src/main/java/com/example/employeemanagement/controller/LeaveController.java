package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.model.Leavee;
import com.example.employeemanagement.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LeaveController {
    @Autowired
    LeaveService leaveService;

    @RequestMapping(value = "/viewAllLeave", method = RequestMethod.GET)
    public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("viewAllLeave");
        mav.addObject(",leave", new Leavee());

        List<Leavee> listOfEmployees = leaveService.getAllEmployees();

        mav.addObject("listOfLeaves", listOfEmployees);

        return mav;
    }

    @RequestMapping(value = "/viewAllLeaveForEmployee", method = RequestMethod.GET)
    public ModelAndView showALLHomeForEmployee(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("viewAllLeaveForEmployee");
        mav.addObject(",leave", new Leavee());

        List<Leavee> listOfEmployees = leaveService.getAllEmployees();

        mav.addObject("listOfLeaves", listOfEmployees);

        return mav;
    }

    @GetMapping("/addNewLeave") //from the nav button by an admin
    public String to_show_new_leave_form(Model model){
        Leavee leavee = new Leavee();
        model.addAttribute("leavee",leavee);
        return "addLeave";
    }
    //applyForLeaveForm

    @GetMapping("/applyForLeaveForm")
    public String to_show_form_for_new_leave_application(Model model){
        Leavee leavee = new Leavee();
        model.addAttribute("leavee",leavee);
        return "applyForLeave";

    }
    @PostMapping("/applyForNewLeave") //from the form post by an employee
    public String displayFormForLeaveApplication(@ModelAttribute("leavee") Leavee leavee) {
        System.out.println("dsjfkff "+leavee.getEmail());
        if(leaveService.saveLeave(leavee)!=null){
            System.out.println("jbc,kbdsc,bcduc,bducicbd");
            return "redirect:/viewAllLeaveForEmployee";
        }
        return "redirect:/viewAllLeaveForEmployee";
    }

    @PostMapping("/addNewLeave") //from the form post
    public String showNewLeaveForm(@ModelAttribute("leavee") Leavee leavee) {
        System.out.println("I entered addNEW "+leavee);
        if(leaveService.saveLeave(leavee)!=null){
            return "redirect:/viewAllLeave?success";
        } else
       return "redirect:/viewAllLeave?error";
    }


    @PostMapping("/editLeave")
    public String editLeave(@ModelAttribute("leavee") Leavee leavee){
        if(leaveService.editLeave(leavee)!=null) {
            return "redirect:/viewAllLeave?success_update";
        }
        else
        return "redirect:/viewAllLeave?error";
    }

    @GetMapping("/showLeaveUpdateForm/{id}")
    public  String show_update_form_leave(@PathVariable(value = "id") long id, Model model){
        //getting employee by id from the service
        Leavee leavee = leaveService.getEmployeeById(id);
        //set employee as a model to prepopulate the form
        model.addAttribute("leavee",leavee);
        return "update_leave";

    }
    @GetMapping("showLeaveDeleteForm/{id}")
    public String show_delete_form_leave(@PathVariable(value = "id")long id){
        //call delete employee method
        this.leaveService.deleteLeaveById(id);
        return "redirect:/viewAllLeave";

    }


}
