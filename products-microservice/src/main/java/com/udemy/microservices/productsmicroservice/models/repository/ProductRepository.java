package com.udemy.microservices.productsmicroservice.models.repository;

import com.udemy.microservices.productsmicroservice.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
