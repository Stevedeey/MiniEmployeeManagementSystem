package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.Login;
import com.example.employeemanagement.model.Admin;
import com.example.employeemanagement.service.AdminServices;
import com.example.employeemanagement.service.serviceImplementation.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
    @Autowired
    private AdminServiceImpl adminServicesImpl;


    @GetMapping("/")
    public String showHome()
    {
        return "index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.removeAttribute("message");

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("admin", new Admin());
        mav.addObject("login", new Login());


        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public String loginProcess(HttpServletRequest request, HttpServletResponse response,
                               @ModelAttribute("login") Login login) {

        Admin user = adminServicesImpl.getUser(login.getEmail(), login.getPassword());

        HttpSession httpSession = request.getSession();

        if (user != null) {
            httpSession.setAttribute("user", user);

            return "redirect:/admin_dashboard";
        } else {
            httpSession.setAttribute("message", "Email or Password is wrong!!!");

            return "redirect:/";
        }
    }


    @RequestMapping(value = "/processLogout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

}
