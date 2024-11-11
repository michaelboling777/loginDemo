package edu.missouristate.controller;

import edu.missouristate.domain.User;
import edu.missouristate.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller

public class IndexController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    public String getDashboard(@ModelAttribute("messageType") String messageType,
                               @ModelAttribute("messageBody") String message, HttpServletRequest request, HttpSession session) {
        User user = userDetailsService.getLogginInUser();
        session.setAttribute("user", user);
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("STUDENT")) {
            return "redirect:/student";
        }
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdmin() {
        return "admindashboard";
    }
    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/student")
    public String getStudent() {
        return "studentdashboard";
    }

}
