package com.udemy.microservices.itemmicroservice.services;

import com.udemy.microservices.itemmicroservice.models.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findById(final Long productId, final Integer quantity);
}
