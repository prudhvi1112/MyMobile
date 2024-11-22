package com.mymobile.entity;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    private String productId;
    private String productModel;
    private double productPrice;

    @Lob
    private byte[] productImage;

    public Product() {
    }

    public Product(String productId, String productModel, double productPrice, byte[] productImage) {
        this.productId = productId;
        this.productModel = productModel;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }
}
