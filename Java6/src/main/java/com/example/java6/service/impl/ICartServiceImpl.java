package com.example.java6.service.impl;

import com.example.java6.entity.Cart;
import com.example.java6.entity.Product;
import com.example.java6.repository.ICartResponsitory;
import com.example.java6.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICartServiceImpl implements CartService {
    @Autowired
    public ICartResponsitory cartResponsitory;

    @Override
    public Cart add(Cart cart) {
        return cartResponsitory.save(cart);
    }

    @Override
    public List<Cart> list() {
        return cartResponsitory.findAll();
    }

    @Override
    public void delete(Integer id) {
cartResponsitory.deleteById(id);
    }

    @Override
    public Cart getByProduct(Product product) {
        return cartResponsitory.findByProduct(product);
    }
}
