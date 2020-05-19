package com.udemy.microservices.productsmicroservice.services;

import com.udemy.microservices.productsmicroservice.models.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(final Long id);
    Product createProduct(final Product product);
}
