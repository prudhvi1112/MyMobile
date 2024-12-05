package com.mymobile.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartIteam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long barcodeid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Cart cart;

	private String productId;
	private String model;

	@Column(columnDefinition = "LONGTEXT")
	private String imageOfProduct;

	private Integer itemQuantity;
	private Double price;
	
	private String status = "InPlace";
}