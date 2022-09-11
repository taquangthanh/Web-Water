package com.example.java6.repository;


import com.example.java6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderResponsitory extends JpaRepository<Order, Integer> {

}
