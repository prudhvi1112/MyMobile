package com.mymobile.dto;

import java.util.List;

import com.mymobile.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {
	private Long cartId;
	private String userId;
	private List<Product> items;

}
