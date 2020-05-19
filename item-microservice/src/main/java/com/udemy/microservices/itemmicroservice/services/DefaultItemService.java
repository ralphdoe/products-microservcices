package com.udemy.microservices.itemmicroservice.services;

import com.udemy.microservices.itemmicroservice.models.Item;
import com.udemy.microservices.itemmicroservice.models.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service("defaultItemService")
public class DefaultItemService implements ItemService {

    @Value("${default.quantity}")
    private Integer defaultQuantity = 1;
    @Value("${service.name}")
    private String serviceName;

    @Resource
    private RestTemplate restClient;

    @Override
    public List<Item> findAll() {
        final List<Product> products =
                Arrays.asList(Objects.requireNonNull(restClient.getForObject(serviceName, Product[].class)));
        return products.stream().map(product -> new Item(product, defaultQuantity)).collect(Collectors.toList());
    }

    @Override
    public Item findById(final Long productId, final Integer quantity) {
        final Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("productId", String.valueOf(productId));
        final Product product = restClient.getForObject(serviceName + "/" + productId, Product.class, pathVariables);
        return new Item(product, quantity);
    }
}
