package com.example.java6.api;

import com.example.java6.dto.request.AuthNewRequest;
import com.example.java6.dto.request.AuthRequest;
import com.example.java6.dto.response.AuthResponse;
import com.example.java6.entity.User;
import com.example.java6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserApi {
    @Autowired
    private UserService userService;

    @PostMapping("/register-new")
    private String newUser(@ModelAttribute("userDTO") AuthNewRequest authNewRequest, BindingResult result, Model model){
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
