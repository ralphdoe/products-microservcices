package com.udemy.microservices.itemmicroservice.clients;

import com.udemy.microservices.itemmicroservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductClientRest {

    @GetMapping("/")
    List<Product> findAll();

    @GetMapping("/{id}")
    Product findById(@PathVariable final Long id);
}
