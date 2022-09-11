package com.example.java6.repository;

import com.example.java6.entity.Product;
import com.example.java6.entity.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByType(ProductType type);
    @Query("SELECT c FROM Product c WHERE c.name like ?1")
    List<Product> findByNameLike(String name);
    @Query("SELECT c FROM Product c WHERE c.name like ?1 and c.type=?2")
    List<Product> findByNameLikeAndType(String name,ProductType type);

    @Query("SELECT c FROM Product c WHERE c.status = 0")
    Page<Product> findByStatus(Pageable pageable);

    @Query("SELECT c FROM Product c WHERE c.status = 0 and c.name like ?1")
    List<Product> findAllAndNameLike(String name);

    @Query("SELECT c FROM Product c WHERE c.status = 0")
    List<Product> findAll();

}
