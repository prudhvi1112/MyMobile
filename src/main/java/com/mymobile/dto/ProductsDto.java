package com.mymobile.dto;

import lombok.Data;

@Data
public class ProductsDto 
{
	private String productId;

	private String description;

	private String brand;

	private Double price;

	private Integer quantity;

	private String color;

	private String model;

	private byte[] imageOfProduct;

	private String productFeatures;

}
