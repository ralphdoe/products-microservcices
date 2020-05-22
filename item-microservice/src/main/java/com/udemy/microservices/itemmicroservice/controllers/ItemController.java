package com.udemy.microservices.itemmicroservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.udemy.microservices.itemmicroservice.models.Item;
import com.udemy.microservices.itemmicroservice.models.Product;
import com.udemy.microservices.itemmicroservice.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("feignItemService")
    private ItemService itemService;

    @GetMapping("/")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "getItemWhenNull")
    @GetMapping("/detail/{productId}/quantity/{quantity}")
    public Item getItem(@PathVariable final Long productId, @PathVariable final Integer quantity) {
        return itemService.findById(productId, quantity);
    }

    public Item getItemWhenNull(@PathVariable final Long productId, @PathVariable final Integer quantity) {
        return new Item(new Product(productId, "Default Product", 10000d, new Date(), 0), quantity);
    }
}
