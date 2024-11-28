package com.mymobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mymobile.dto.CartResponseDto;
import com.mymobile.entity.Cart;
import com.mymobile.entity.CartIteam;
import com.mymobile.exception.Response;
import com.mymobile.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/{customerId}")
	public ResponseEntity<List<CartIteam>> getCart(@PathVariable String customerId) {
		return new ResponseEntity<>(cartService.getCartItems(customerId), HttpStatus.OK);
	}

	@PostMapping("addtocart/{customerId}")
	public ResponseEntity<CartIteam> addToCart(@PathVariable String customerId, @RequestBody CartIteam cartItem) {
		return new ResponseEntity<>(cartService.addToCart(customerId, cartItem), HttpStatus.OK);
	}

	@DeleteMapping("/remove")
	public ResponseEntity<?> removeProductFromCart(@RequestParam String userId, @RequestParam String productId) {
		cartService.removeFromCart(userId, productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/checkout/{customerId}")
	public ResponseEntity<?> checkoutCart(@PathVariable String customerId, @RequestBody List<CartIteam> cartIteams) {
		cartService.cartCheckout(customerId, cartIteams);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
