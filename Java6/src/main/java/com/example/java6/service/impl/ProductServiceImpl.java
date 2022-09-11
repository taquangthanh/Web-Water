package com.example.java6.service.impl;

import com.example.java6.dto.request.ProductRequest;
import com.example.java6.entity.Product;
import com.example.java6.repository.ProductRepo;
import com.example.java6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    public ProductRepo productRepo;
    @Override
    public Product add(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setProducer(productRequest.getProducer());
        product.setCreatedDate();
        product.setPrice(productRequest.getPrice());
        product.setPhoto(productRequest.getPhoto());
        product.setQuantity(productRequest.getQuantity());
        product.setStatus(0);
        product.setType(productRequest.getType());
        return productRepo.save(product);
    }
    @Override
    public Product addForOder(Product productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setProducer(productRequest.getProducer());
        product.setCreatedDate();
        product.setPrice(productRequest.getPrice());
        product.setPhoto(productRequest.getPhoto());
        product.setQuantity(productRequest.getQuantity());
        product.setStatus(0);
        product.setType(productRequest.getType());
        return productRepo.save(product);
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        return null;
    }

    @Override
    public Product update(Integer id, ProductRequest productRequest) {
         Optional<Product> optionalProduct =  productRepo.findById(id);
         if (optionalProduct.isPresent()){
             Product product = optionalProduct.get();
             product.setName(productRequest.getName());
             product.setProducer(productRequest.getProducer());
             product.setCreatedDate();
             product.setPrice(productRequest.getPrice());
             product.setPhoto(productRequest.getPhoto()==null?product.getPhoto(): productRequest.getPhoto());
             product.setQuantity(productRequest.getQuantity());
             product.setStatus(0);
             product.setType(productRequest.getType());
             return productRepo.save(product) ;
         }
        return null;
    }

    @Override
    public Product updateProduct(Integer id, Product productRequest) {
        Optional<Product> optionalProduct =  productRepo.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setQuantity(productRequest.getQuantity());
            return productRepo.save(product) ;
        }
        return null;
    }

    @Override
    public Product delete(Integer id, Product product) {
        Optional<Product> optionalProduct =  productRepo.findById(id);
        if (optionalProduct.isPresent()){
            Product p = optionalProduct.get();
            p.setName(product.getName());
            p.setProducer(product.getProducer());
            p.setCreatedDate();
            p.setPrice(product.getPrice());
            p.setPhoto(product.getPhoto()==null?p.getPhoto(): product.getPhoto());
            p.setQuantity(product.getQuantity());
            p.setStatus(1);
            p.setType(product.getType());
            return productRepo.save(product) ;
        }
        return null;
    }


    @Override
    public Page<Product> getAllPage(Integer page) {
        return productRepo.findByStatus(PageRequest.of(page, 5));
    }

    @Override
    public List<Product> getAllByName(String name) {
        return productRepo.findAllAndNameLike("%" + name + "%");
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }
}
