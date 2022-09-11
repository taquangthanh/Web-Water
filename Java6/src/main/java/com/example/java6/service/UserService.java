package com.example.java6.service;

import com.example.java6.dto.request.AuthNewRequest;
import com.example.java6.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUserName(String email);
    User save (AuthNewRequest authNewRequest);
}
