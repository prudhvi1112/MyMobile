package com.mymobile.service;

import com.mymobile.entity.Cart;
import com.mymobile.entity.Product;
import com.mymobile.entity.UserData;
import com.mymobile.exception.CartNotFoundException;
import com.mymobile.exception.InvaildUserException;
import com.mymobile.exception.ProductOutOfStockException;
import com.mymobile.exception.ProductsNotFoundException;
import com.mymobile.repo.CartRepository;
import com.mymobile.repo.ProductDao;
import com.mymobile.repo.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CartService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductDao productRepository;

	public Cart getCart(String customerId) {
		UserData user = userDetailsDao.findById(customerId)
				.orElseThrow(() -> new InvaildUserException("User not found  with Id : " + customerId));
		Cart cartData = cartRepository.findByUserData(user);
		if (cartData == null) {
			return new Cart(null, null, null);
		} else {
			return cartData;
		}
	}

	@Transactional
	public Cart addToCart(String customerId, String productId, int quantity) {
		UserData user = userDetailsDao.findById(customerId)
				.orElseThrow(() -> new InvaildUserException("User not found with Id : " + customerId));
		Cart cartData = cartRepository.findByUserData(user);
		Cart cart;
		if (cartData == null) {
			cart = new Cart();
			cart.setUserData(user);
			cart.setProducts(List.of());
			cart = cartRepository.save(cart);
		} else {
			cart = cartData;
		}
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductsNotFoundException("Product not found with Id : " + productId));
		boolean productExistsInCart = false;
		for (Product cartProduct : cart.getProducts()) {
			if (cartProduct.equals(product)) {
				if (product.getQuantity() >= quantity) {
					cartProduct.setQuantity(quantity);
					productExistsInCart = true;
				} else {
					throw new ProductOutOfStockException("Not enough stock for product: " + productId);
				}
				break;
			}
		}
		if (!productExistsInCart) {
			if (product.getQuantity() >= quantity) {
				product.setQuantity(product.getQuantity() - quantity);
				cart.getProducts().add(product);
			} else {
				throw new ProductOutOfStockException("Not enough stock for product: " + productId);
			}
		}
		cart = cartRepository.save(cart);
		return cart;
	}

	@Transactional
	public boolean removeProductFromCart(String userId, String productId) {

		UserData user = userDetailsDao.findById(userId)
				.orElseThrow(() -> new InvaildUserException("User not found with Id " + userId));

		Cart cart = cartRepository.findByUserData(user);
		if (cart == null) {
			throw new CartNotFoundException("Cart not found for userId : " + userId);
		}

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ProductsNotFoundException("Product not found with Id : " + productId));

		cart.getProducts().remove(product);

		cartRepository.save(cart);

		return true;
	}

}
