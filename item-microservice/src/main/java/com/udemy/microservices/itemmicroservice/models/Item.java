package com.udemy.microservices.itemmicroservice.models;

public class Item {
    private Product product;
    private Integer quantity;

    public Item() {
    }

    public Item(final Product product, final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return quantity * product.getPrice();
    }
}
