package com.mymobile.dto;

import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {

	@NotEmpty
	private String vendorId;

	@Size(min = 3, max = 35, message = "Username must be between 3 and 50 characters")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$", message = "User ID must be alphanumeric and contain at least one letter and one digit.")
	private String productId;

	@NotNull
	@Size(min = 1, max = 5000)
	private String description;

	@NotNull
	private String brand;

	@NotNull
	@Positive
	private Double price;

	@NotNull
	@Positive
	private Integer quantity;

	@NotNull
	private String color;

	@NotNull
	private String model;

	private String imageOfProduct;

	@Size(min = 1, max = 10000)
	private String productFeatures;

}
