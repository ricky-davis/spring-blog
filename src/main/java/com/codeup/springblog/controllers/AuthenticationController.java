package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.services.AuthenticationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    private AuthenticationService authSvc;

    public AuthenticationController(AuthenticationService authSvc){
        this.authSvc = authSvc;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        if(authSvc.getCurUser() == null){
            return "redirect:/posts";
        }
        return "users/login";
    }
}