package com.mymobile.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
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
	
	 @Column( columnDefinition = "LONGTEXT" )
	 private String imageOfProduct;

//	@Lob 
//	private byte[] imageOfProduct;

	@Size(min = 1, max = 10000)
	private String productFeatures;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	@JsonIgnore
    private UserData userData; 
	
	
//	@ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
//	 @JsonBackReference 
//    private List<Cart> carts;
	
}
