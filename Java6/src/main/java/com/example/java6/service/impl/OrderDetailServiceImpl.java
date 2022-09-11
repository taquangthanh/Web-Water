package com.example.java6.service.impl;

import com.example.java6.entity.Order;
import com.example.java6.entity.OrderDetail;
import com.example.java6.repository.IOrderDetailResponsitory;
import com.example.java6.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    public IOrderDetailResponsitory orderDetailResponsitory;

    @Override
    public OrderDetail add(OrderDetail orderDetail) {
        return orderDetailResponsitory.save(orderDetail);
    }

    @Override
    public List<OrderDetail> listOrderDetail(Order order) {
        return orderDetailResponsitory.findByOrder(order);
    }
}
