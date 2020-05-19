package com.udemy.microservices.itemmicroservice.controllers;

import com.udemy.microservices.itemmicroservice.models.Item;
import com.udemy.microservices.itemmicroservice.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/detail/{productId}/quantity/{quantity}")
    public Item getItem(@PathVariable final Long productId, @PathVariable final Integer quantity) {
        return itemService.findById(productId, quantity);
    }
}
