package com.example.java6.service;

import com.example.java6.dto.request.ProductRequest;
import com.example.java6.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addForOder(Product productRequest);
    Product add(ProductRequest productRequest);
    Product findById(Integer id);
    Product update(Integer id,ProductRequest productRequest);
    Product updateProduct(Integer id,Product productRequest);
    Product delete(Integer id,Product product);
    Page<Product> getAllPage(Integer page);
    List<Product> getAllByName(String name);
    List<Product> getAll();
}
