package com.mymobile.service;

import com.mymobile.entity.Cart;
import com.mymobile.repo.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {

        this.cartRepository = cartRepository;
    }
    public Cart saveCart(Cart cart){

        return cartRepository.save(cart);
    }
    public Cart getCartById(String cartId){

        return cartRepository.findById(cartId).orElse(null);
    }

}