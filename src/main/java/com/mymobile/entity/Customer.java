package com.mymobile.entity;

import jakarta.persistence.*;
import java.util.List;
@Entity
public class Customer {
     @Id
    private String customerId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Product> productList;

    public Customer() {
    }

    public Customer(String customerId, List<Product> productList) {
        this.customerId = customerId;
        this.productList = productList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
