package com.udemy.microservices.itemmicroservice.models;

import java.util.Date;

public class Product {
    private Long id;
    private String name;
    private Double price;
    private Date createdAt;
    private Integer port;

    public Product() {
    }

    public Product(Long id, String name, Double price, Date createdAt, Integer port) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
