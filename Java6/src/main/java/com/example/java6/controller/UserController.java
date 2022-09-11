package com.example.java6.controller;

import com.example.java6.dto.request.AuthNewRequest;
import com.example.java6.entity.User;
import com.example.java6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    private String showLogin(){
        return "views/User/login";
    }
    @GetMapping("/register")
    private String showRegister(Model model){
        model.addAttribute("userDTO",new AuthNewRequest());
        return "views/User/register";
    }
    @PostMapping("/register-new")
    private String newUser(@Valid @ModelAttribute("userDTO") AuthNewRequest authNewRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("userDTO",authNewRequest);
            result.toString();
            return "views/User/register";
        }
        User user = userService.findByUserName(authNewRequest.getUsername());
        System.out.println(user);
        if(user!=null){
            System.out.println("Email đã được sử dụnng");
            return "views/User/register";
        }
        if (authNewRequest.getPassword().equalsIgnoreCase(authNewRequest.getRepeatPassword())){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            authNewRequest.setPassword(encoder.encode(authNewRequest.getPassword()));
            userService.save(authNewRequest);
            model.addAttribute("userDTO", authNewRequest);
            System.out.println("Successfully");
            return "redirect:/product";
        }else {
            model.addAttribute("userDTO", authNewRequest);
            System.out.println("Password is't same");
            return "views/User/register";
        }
    }
}
