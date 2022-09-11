package com.example.java6.service;

import com.example.java6.entity.Cart;
import com.example.java6.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
     Cart add(Cart cart);
     List<Cart> list();
     void delete(Integer id);
     Cart getByProduct(Product product);
}
