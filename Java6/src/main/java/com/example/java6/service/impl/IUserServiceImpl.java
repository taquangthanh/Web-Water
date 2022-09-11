package com.example.java6.service.impl;

import com.example.java6.dto.request.AuthNewRequest;
import com.example.java6.entity.User;
import com.example.java6.repository.RoleRepo;
import com.example.java6.repository.UserRepo;
import com.example.java6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IUserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public User findByUserName(String email) {
        return userRepo.findByUsername(email);
    }

    @Override
    public User save(AuthNewRequest authNewRequest) {
        User user = new User();
        user.setUsername(authNewRequest.getUsername());
        user.setPassword(authNewRequest.getPassword());
        user.setName(authNewRequest.getName());
        user.setRoles(Arrays.asList(roleRepo.findByName("Admin")));
        return userRepo.save(user);
    }
}
