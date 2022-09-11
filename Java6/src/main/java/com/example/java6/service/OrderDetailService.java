package com.example.java6.service;

import com.example.java6.entity.Order;
import com.example.java6.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    OrderDetail add(OrderDetail orderDetail);
    List<OrderDetail> listOrderDetail(Order order);
}
