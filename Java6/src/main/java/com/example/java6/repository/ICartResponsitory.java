package com.example.java6.repository;


import com.example.java6.entity.Cart;
import com.example.java6.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartResponsitory extends JpaRepository<Cart, Integer> {
	@Query("select o from Cart o where o.product=?1")
	Cart findByProduct(Product product);
}
