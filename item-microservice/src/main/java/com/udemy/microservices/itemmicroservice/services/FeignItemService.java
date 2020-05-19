package com.udemy.microservices.itemmicroservice.services;

import com.udemy.microservices.itemmicroservice.clients.ProductClientRest;
import com.udemy.microservices.itemmicroservice.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("feignItemService")
public class FeignItemService implements ItemService {

    @Value("${default.quantity}")
    private final Integer defaultQuantity = 1;

    @Autowired
    private ProductClientRest feignClient;

    @Override
    public List<Item> findAll() {
        return feignClient.findAll().stream().map(product -> new Item(product, defaultQuantity)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long productId, Integer quantity) {
        return new Item(feignClient.findById(productId), quantity);
    }
}
