package com.udemy.microservices.itemmicroservice.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.udemy.microservices.itemmicroservice.models.Item;
import com.udemy.microservices.itemmicroservice.models.Product;
import com.udemy.microservices.itemmicroservice.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ItemController {

    @Autowired
    @Qualifier("feignItemService")
    private ItemService itemService;

    @Value("${text.configuration}")
    private String textConfiguration;

    @Autowired
    private Environment environment;

    private static Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

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

    @GetMapping("get-config")
    public ResponseEntity<?> getConfig(@Value("${server.port}") final String port) {
        final Map<String, String> json = new HashMap<>();
        json.put("textConfiguration", textConfiguration);
        json.put("port", port);
        LOGGER.info("Text Configuration: " + textConfiguration);

        if (environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equals("dev")) {
            json.put("configuration.name", environment.getProperty("configuration.name"));
            json.put("configuration.email", environment.getProperty("configuration.email"));
        }

            return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

}
