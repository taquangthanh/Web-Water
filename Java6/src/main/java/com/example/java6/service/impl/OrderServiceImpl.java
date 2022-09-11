package com.example.java6.service.impl;

import com.example.java6.entity.Order;
import com.example.java6.repository.IOderResponsitory;
import com.example.java6.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public IOderResponsitory oderResponsitory;

    @Override
    public Order add(Order order) {
        return oderResponsitory.save(order);
    }

    @Override
    public List<Order> list() {
        return oderResponsitory.findAll();
    }

    @Override
    public Order getId(Integer id) {
        return oderResponsitory.findById(id).get();
    }
}
