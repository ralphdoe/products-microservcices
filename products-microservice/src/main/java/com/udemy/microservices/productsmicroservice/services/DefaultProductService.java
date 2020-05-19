package com.udemy.microservices.productsmicroservice.services;

import com.udemy.microservices.productsmicroservice.models.entity.Product;
import com.udemy.microservices.productsmicroservice.models.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultProductService implements ProductService {
    @Resource
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(final Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }
}
