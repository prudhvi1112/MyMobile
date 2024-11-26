package com.mymobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mymobile.entity.Cart;
import com.mymobile.exception.Response;
import com.mymobile.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/{customerId}")
	public ResponseEntity<Cart> getCart(@PathVariable String customerId) {
		return new ResponseEntity<Cart>(cartService.getCart(customerId), HttpStatus.OK);
	}

	@PostMapping("/{customerId}/products/{productId}")
	public ResponseEntity<Cart> addToCart(@PathVariable String customerId, @PathVariable String productId,
			@RequestParam Integer quantity) {

		cartService.addToCart(customerId, productId, quantity);
		return new ResponseEntity<Cart>(HttpStatus.OK);
	}

	@DeleteMapping("/remove")
	public ResponseEntity<?> removeProductFromCart(@RequestParam String userId, @RequestParam String productId) {
		cartService.removeProductFromCart(userId, productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
