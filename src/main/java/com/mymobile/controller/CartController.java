package com.mymobile.controller;

import com.mymobile.entity.Cart;
import com.mymobile.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {

        this.cartService = cartService;
    }
    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart){
        Cart savedcart= cartService.saveCart(cart);
        return new ResponseEntity(savedcart,HttpStatus.OK);
    }
    @GetMapping("/{cartId}")
    public  ResponseEntity<Cart> getCart(@PathVariable String cartId){
        Cart cart = cartService.getCartById(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
