package com.udemy.microservices.productsmicroservice.controllers;

import com.udemy.microservices.productsmicroservice.models.entity.Product;
import com.udemy.microservices.productsmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping(value = "/")
    public List<Product> findAll() {
        return productService.findAll().stream().map(product -> {
            product.setPort(port);
            return product;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable final Long id) throws Exception {
        final Product product = productService.findById(id);
        if (product == null) {
            throw new Exception("Product doesn't exist.");
        }
        //Thread.sleep(2000);
        product.setPort(port);
        return product;
    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody final Product product) {
        return productService.createProduct(product);
    }
}
