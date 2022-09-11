package com.example.java6.controller;

import com.example.java6.entity.Cart;
import com.example.java6.entity.User;
import com.example.java6.service.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public abstract class BaseController {
    private final CartService cartService;

    protected BaseController(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("size")
    private Integer getSize() {
        List<Cart> carts = cartService.list();
        Integer size = carts.size();
        if (size>0){
            return size;
        }
        return 0;
    }

    @ModelAttribute("userLogined")
    public User getUserLogined() {
        Object userLogined = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userLogined != null && userLogined instanceof UserDetails)
            return (User) userLogined;
        return null;
    }
}
