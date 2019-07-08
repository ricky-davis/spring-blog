package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.AuthenticationService;
import com.codeup.springblog.services.HaveIBeenPwndService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private HaveIBeenPwndService hibp;
    private AuthenticationService authSvc;

    public UserController(AuthenticationService authSvc,UserRepository users, PasswordEncoder passwordEncoder, HaveIBeenPwndService hibp) {
        this.authSvc = authSvc;
        this.userDao = users;
        this.passwordEncoder = passwordEncoder;
        this.hibp = hibp;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        if(authSvc.getCurUser() == null){
            return "redirect:/posts";
        }
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Valid User user,
                           Errors validation,
                           Model model){
        if(authSvc.getCurUser() == null){
            return "redirect:/posts";
        }
        if(userDao.findByEmail(user.getEmail()) != null){
            validation.rejectValue("email",null,"This email is already in use!");
        }
        if(userDao.findByUsername(user.getUsername()) != null){
            validation.rejectValue("username",null,"This username already exists!");
        }
        if(hibp.CheckPasswordForPwnage(user.getPassword())){
            validation.rejectValue("password",null,
                    "This password has been compromised in the past!");
        }
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "users/sign-up";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
}