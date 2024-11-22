package com.mymobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mymobile.dto.ProductAddedResponse;
import com.mymobile.dto.ProductDto;
import com.mymobile.dto.ProductsDto;
import com.mymobile.dto.UserDataDto;
import com.mymobile.service.VendorService;

import jakarta.validation.Valid;

@RestController
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping("/vendor/addProduct")
	public ResponseEntity<ProductAddedResponse> addProduct(@Valid @RequestBody ProductDto productDto) {
		return new ResponseEntity<>(vendorService.createProduct(productDto), HttpStatus.CREATED);
	}

	@GetMapping({ "/products", "/vendor/products/{vendorId}" })
	public ResponseEntity<List<ProductsDto>> getProducts(@PathVariable(required = false) String vendorId) {
		return new ResponseEntity<>(vendorService.getProductsList(vendorId), HttpStatus.OK);
	}

}