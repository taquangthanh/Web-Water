package com.example.java6.service;

import com.example.java6.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order add(Order order);
    List<Order> list();
    Order getId(Integer id);
}
