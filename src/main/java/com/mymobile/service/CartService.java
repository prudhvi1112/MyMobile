package com.mymobile.service;

import com.mymobile.entity.Cart;
import com.mymobile.entity.CartIteam;
import com.mymobile.entity.Product;
import com.mymobile.entity.UserData;
import com.mymobile.exception.BarcodeNotFoundException;
import com.mymobile.exception.InvaildUserException;
import com.mymobile.exception.InvalidCheckOutException;
import com.mymobile.exception.ProductOutOfStockException;
import com.mymobile.exception.ProductsNotFoundException;
import com.mymobile.repo.CartIteamRepositary;
import com.mymobile.repo.CartRepository;
import com.mymobile.repo.ProductDao;
import com.mymobile.repo.UserDetailsDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductDao productRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CartIteamRepositary cartItemRepository;

	public CartIteam addToCart(String userId, CartIteam cartItem) {

		Cart cart = cartRepository.findById(userId).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setUserId(userId);
			// newCart.setStatus("InPlace");
			return cartRepository.save(newCart);
		});

		Optional<List<CartIteam>> existingItem = cartItemRepository.findByCartUserIdAndProductId(userId,
				cartItem.getProductId());

		if (existingItem.isPresent()) {
			for (CartIteam item : existingItem.get()) {
				if (item.getStatus().equalsIgnoreCase("InPlace")) {
					item.setItemQuantity(cartItem.getItemQuantity());
					cartItemRepository.save(item); // Save each updated item
				}
			}
		}

//		if (existingItem.isPresent() && (existingItem.get().getStatus().equalsIgnoreCase("InPlace"))) {
//			CartIteam item = existingItem.get();
//			item.setItemQuantity(cartItem.getItemQuantity());
//			return cartItemRepository.save(item);
//		}

		cartItem.setCart(cart);
		return cartItemRepository.save(cartItem);
	}

	@Transactional
	public void removeFromCart(String userId, String productId) {
		// Find the list of items in the cart with the provided userId and productId
		Optional<List<CartIteam>> existingItems = cartItemRepository.findByCartUserIdAndProductId(userId, productId);

		// If no items are found or the list is empty, throw an exception
		if (!existingItems.isPresent() || existingItems.get().isEmpty()) {
			throw new RuntimeException("Cart item not found");
		}

		// Iterate through the list of items (even though it's just one item in your
		// case, handle multiple if needed)
		for (CartIteam item : existingItems.get()) {
			// Delete the item from the cart
			cartItemRepository.delete(item);
		}
	}

//	@Transactional
//	public void removeFromCart(String userId, String productId) {
//		CartIteam item = cartItemRepository.findByCartUserIdAndProductId(userId, productId)
//				.orElseThrow(() -> new RuntimeException("Cart item not found"));
//		cartItemRepository.delete(item);
//
//	}

	public List<CartIteam> getCartItems(String userId) {
		List<CartIteam> byCartUserId = cartItemRepository.findByCartUserId(userId);
		List<CartIteam> response = byCartUserId.stream()
				.filter(cartIteam -> cartIteam.getStatus().equalsIgnoreCase("InPlace")).collect(Collectors.toList());
		return response;
	}

	@Transactional
	public void clearCart(String userId) {
		Cart cart = cartRepository.findById(userId).orElseThrow(() -> new RuntimeException("Cart not found"));
		cart.getCartItems().clear();
		cartRepository.save(cart);
	}

	@Transactional(rollbackFor = { InvaildUserException.class, ProductsNotFoundException.class,
			InvalidCheckOutException.class, BarcodeNotFoundException.class })
	public void cartCheckout(String customerId, List<CartIteam> cartIteams) {
		int count = cartIteams.size();
		UserData userId = userDetailsDao.findById(customerId)
				.orElseThrow(() -> new InvaildUserException("Invalid User With Id : " + customerId));
		for (CartIteam iteam : cartIteams) {
			Product updatedProduct = null;
			Product product = productRepository.findById(iteam.getProductId()).orElseThrow(
					() -> new ProductsNotFoundException("Product Not Found With Id : " + iteam.getProductId()));
			if (product.getQuantity() < iteam.getItemQuantity()) {
				throw new ProductOutOfStockException("Product out of Stack for Product Id : " + product.getProductId());
			} else {
				product.setQuantity(product.getQuantity() - iteam.getItemQuantity());
				updatedProduct = productRepository.save(product);
			}
			if (updatedProduct != null) {
				count = count + 1;
				CartIteam cartIteam = cartItemRepository.findById(iteam.getBarcodeid())
						.orElseThrow(() -> new BarcodeNotFoundException(
								"Barcode Not Found for Product Id : " + iteam.getProductId()));
				cartIteam.setStatus("Delivered");
				cartItemRepository.save(cartIteam);
			}
		}
//		if (!(count == cartIteams.size())) {
//			throw new InvalidCheckOutException("Something Went wrong in Checkout");
//		}

	}

}

//			CartResponseDto responseDto = new CartResponseDto();
//			
//				    // Find the user
//				    UserData user = userDetailsDao.findById(customerId)
//				            .orElseThrow(() -> new InvaildUserException("User not found with Id: " + customerId));
//		
//				    Cart cartData = cartRepository.findByUserData(user);
//				    Cart cart;
//				    if (cartData == null) {
//				        cart = new Cart();
//				        cart.setUserData(user);
//				        cart.setCartItem(new ArrayList<>());
//				        cart = cartRepository.save(cart);
//				    } else {
//				        cart = cartData;
//				    }
//				    
//			
//				    Product product = productRepository.findById(cartItem.getProductId())
//				            .orElseThrow(() -> new ProductsNotFoundException("Product not found with Id: " + cartItem.getProductId()));
//			
//				    
//				    if (product.getQuantity() < cartItem.getItemQuantity()) {
//				        throw new ProductOutOfStockException("Not enough stock for product: " + cartItem.getProductId());
//				    }
//		 
//				    boolean productExistsInCart = false;
//				    for (CartIteam cartProduct : cart.getCartItem()) {
//				        if (cartProduct.getProductId().equals(cartItem.getProductId())) {
//				            // Update the quantity in the cart's product
//				            cartProduct.setItemQuantity(cartItem.getItemQuantity());  
//				            productExistsInCart = true;
//				            break;
//				        }
//				    }
//				    if (!productExistsInCart) {
//				        // Create a new detached cart product
//				        CartIteam cartProduct = new Product();
//				        cartProduct.setProductId(product.getProductId());
//				        cartProduct.setDescription(product.getDescription());
//				        cartProduct.setBrand(product.getBrand());
//				        cartProduct.setPrice(product.getPrice());
//				        cartProduct.setColor(product.getColor());
//				        cartProduct.setModel(product.getModel());
//				        cartProduct.setQuantity(quantity); // Set the quantity in the cart, not the product table
//				        cartProduct.setProductFeatures(product.getProductFeatures());
//				        cartProduct.setImageOfProduct(product.getImageOfProduct());
//
//				        // Add to the cart (this doesn't affect the product table)
//				        cart.getProducts().add(cartProduct);
//				    }
//
//				    // Save the cart, but no changes will be made to the Product table.
//				    cart = cartRepository.save(cart);
//
//				    // Set up the response DTO
//				    responseDto.setUserId(customerId);
//				    responseDto.setCartId(cart.getCartId());
//				    responseDto.setItems(cart.getProducts());
//
//				    return responseDto;
//				    }

//	public CartResponseDto getCart(String customerId) {
//		UserData user = userDetailsDao.findById(customerId)
//				.orElseThrow(() -> new InvaildUserException("User not found  with Id : " + customerId));
//		Cart cartData = cartRepository.findByUserData(user);
//		
//		CartResponseDto responseDto =new CartResponseDto();
//		
//		responseDto.setCartId(cartData.getCartId());
//		responseDto.setUserId(cartData.getUserData().getUserId());
//		responseDto.setItems(cartData.getProducts());
//		
//		if (cartData == null) {
//			return new CartResponseDto(0L,null,new ArrayList<>()); //Cart(null, null, null);
//		} else {
//			return responseDto;
//		}
//	}
//
//	@Transactional(rollbackFor = { ProductOutOfStockException.class, InvaildUserException.class,
//			ProductsNotFoundException.class })
//	public CartResponseDto addToCart(String customerId, String productId, Integer quantity) {
//		
//		
//		CartResponseDto responseDto = new CartResponseDto();
//
//	    // Find the user
//	    UserData user = userDetailsDao.findById(customerId)
//	            .orElseThrow(() -> new InvaildUserException("User not found with Id: " + customerId));
//
//	    // Retrieve or create cart for the user
//	    Cart cartData = cartRepository.findByUserData(user);
//	    Cart cart;
//	    if (cartData == null) {
//	        cart = new Cart();
//	        cart.setUserData(user);
//	        cart.setProducts(new ArrayList<>());
//	        cart = cartRepository.save(cart);
//	    } else {
//	        cart = cartData;
//	    }
//
//	    // Retrieve the product from the database
//	    Product product = productRepository.findById(productId)
//	            .orElseThrow(() -> new ProductsNotFoundException("Product not found with Id: " + productId));
//
//	    System.out.println(product.getQuantity() + " " + quantity);
//	    if (product.getQuantity() < quantity) {
//	        throw new ProductOutOfStockException("Not enough stock for product: " + productId);
//	    }
//
//	    // Detach the product from the persistence context so that changes don't get reflected in the DB
//	    entityManager.detach(product);
//	    
//	    
//	    boolean productExistsInCart = false;
//	    for (Product cartProduct : cart.getProducts()) {
//	        if (cartProduct.getProductId().equals(product.getProductId())) {
//	            // Update the quantity in the cart's product
//	            cartProduct.setQuantity(quantity);  
//	            productExistsInCart = true;
//	            break;
//	        }
//	    }

//	    boolean productExistsInCart = false;
//	    for (Product cartProduct : cart.getProducts()) {
//	        if (cartProduct.getProductId().equals(product.getProductId())) {
//	        	
//	        	  Product detachedCartProduct = new Product();
//	              detachedCartProduct.setProductId(cartProduct.getProductId());
//	              detachedCartProduct.setDescription(cartProduct.getDescription());
//	              detachedCartProduct.setBrand(cartProduct.getBrand());
//	              detachedCartProduct.setPrice(cartProduct.getPrice());
//	              detachedCartProduct.setColor(cartProduct.getColor());
//	              detachedCartProduct.setModel(cartProduct.getModel());
//	              detachedCartProduct.setProductFeatures(cartProduct.getProductFeatures());
//	              detachedCartProduct.setImageOfProduct(cartProduct.getImageOfProduct());
//	              detachedCartProduct.setQuantity(quantity);  // Update the quantity in the cart's product
//
//	              // Replace the original cart product with the detached cart product
//	              cart.getProducts().remove(cartProduct);  // Remove the old cart product
//	              cart.getProducts().add(detachedCartProduct);  // Add the detached copy with updated quantity
//
//	              productExistsInCart = true;
//	              break;
//	            // Only update the quantity in the cart, not in the Product entity
////	            cartProduct.setQuantity(quantity);
////	            productExistsInCart = true;
////	            break;
//	        }
//	    }

//	    if (!productExistsInCart) {
//	        // Create a new detached cart product
//	        Product cartProduct = new Product();
//	        cartProduct.setProductId(product.getProductId());
//	        cartProduct.setDescription(product.getDescription());
//	        cartProduct.setBrand(product.getBrand());
//	        cartProduct.setPrice(product.getPrice());
//	        cartProduct.setColor(product.getColor());
//	        cartProduct.setModel(product.getModel());
//	        cartProduct.setQuantity(quantity); // Set the quantity in the cart, not the product table
//	        cartProduct.setProductFeatures(product.getProductFeatures());
//	        cartProduct.setImageOfProduct(product.getImageOfProduct());
//
//	        // Add to the cart (this doesn't affect the product table)
//	        cart.getProducts().add(cartProduct);
//	    }
//
//	    // Save the cart, but no changes will be made to the Product table.
//	    cart = cartRepository.save(cart);
//
//	    // Set up the response DTO
//	    responseDto.setUserId(customerId);
//	    responseDto.setCartId(cart.getCartId());
//	    responseDto.setItems(cart.getProducts());
//
//	    return responseDto;
//	    }

//		CartResponseDto responseDto=new CartResponseDto();
//
//		UserData user = userDetailsDao.findById(customerId)
//				.orElseThrow(() -> new InvaildUserException("User not found with Id: " + customerId));
//
//		Cart cartData = cartRepository.findByUserData(user);
//		Cart cart;
//		if (cartData == null) {
//			cart = new Cart();
//			cart.setUserData(user);
//			cart.setProducts(new ArrayList<>());
//			cart = cartRepository.save(cart);
//		} else {
//			cart = cartData;
//		}
//
//		Product product = productRepository.findById(productId)
//				.orElseThrow(() -> new ProductsNotFoundException("Product not found with Id: " + productId));
//System.out.println(product.getQuantity()+ " "+quantity);
//		if (product.getQuantity() < quantity) {
//			throw new ProductOutOfStockException("Not enough stock for product: " + productId);
//		}
//
//		
//		 boolean productExistsInCart = false;
//		    for (Product cartProduct : cart.getProducts()) {
//		        if (cartProduct.getProductId().equals(product.getProductId())) {
//		            // Only update the quantity in the cart, not in the Product entity
//		            cartProduct.setQuantity(quantity);
//		            productExistsInCart = true;
//		            break;
//		        }
//		    }
////		boolean productExistsInCart = false;
////		for (Product cartProduct : cart.getProducts()) {
////			if (cartProduct.equals(product)) {
////
////				cartProduct.setQuantity(quantity);
////				productExistsInCart = true;
////				break;
////			}
////		}
//		
//		if (!productExistsInCart) {
//	        // Add the product to the cart with the requested quantity
//	        // Ensure the product in cart does not trigger an update in the Product table
//	        Product cartProduct = new Product();
//	        cartProduct.setProductId(product.getProductId());
//	        cartProduct.setDescription(product.getDescription());
//	        cartProduct.setBrand(product.getBrand());
//	        cartProduct.setPrice(product.getPrice());
//	        cartProduct.setColor(product.getColor());
//	        cartProduct.setModel(product.getModel());
//	        cartProduct.setQuantity(quantity);
//	        cartProduct.setProductFeatures(product.getProductFeatures());
//	        cartProduct.setImageOfProduct(product.getImageOfProduct());
//	        
//	        cart.getProducts().add(cartProduct);
//	    }
//
////		if (!productExistsInCart) {
////			product.setQuantity(quantity);
////			cart.getProducts().add(product);
////		}
//
//		cart = cartRepository.save(cart);
//		
//		responseDto.setUserId(customerId);
//		responseDto.setCartId(cart.getCartId());
//		responseDto.setItems(cart.getProducts());
//		
//
//		return responseDto;

//	@Transactional(rollbackFor = {ProductOutOfStockException.class, InvaildUserException.class,ProductsNotFoundException.class})
//	public Cart addToCart(String customerId, String productId, Integer quantity) {
//		UserData user = userDetailsDao.findById(customerId)
//				.orElseThrow(() -> new InvaildUserException("User not found with Id : " + customerId));
//		Cart cartData = cartRepository.findByUserData(user);
//		Cart cart;
//		if (cartData == null) {
//			cart = new Cart();
//			cart.setUserData(user);
//			 cart.setProducts(new ArrayList<>()); 
//			cart = cartRepository.save(cart);
//		} else {
//			cart = cartData;
//		}
//		Product product = productRepository.findById(productId)
//				.orElseThrow(() -> new ProductsNotFoundException("Product not found with Id : " + productId));
//		boolean productExistsInCart = false;
//		for (Product cartProduct : cart.getProducts()) {
//			if (cartProduct.equals(product)) {
//				if (product.getQuantity() >= quantity) {
//					cartProduct.setQuantity(quantity);
//					productExistsInCart = true;
//				} else {
//					throw new ProductOutOfStockException("Not enough stock for product: " + productId);
//				}
//				break;
//			}
//		}
//		if (!productExistsInCart) {
//			if (product.getQuantity() >= quantity) {
//				product.setQuantity(product.getQuantity() - quantity);
//				cart.getProducts().add(product);
//			} else {
//				throw new ProductOutOfStockException("Not enough stock for product: " + productId);
//			}
//		}
//		cart = cartRepository.save(cart);
//		return cart;
//	}

//	@Transactional
//	public boolean removeProductFromCart(String userId, String productId) {
//
//		UserData user = userDetailsDao.findById(userId)
//				.orElseThrow(() -> new InvaildUserException("User not found with Id " + userId));
//
//		Cart cart = cartRepository.findByUserData(user);
//		if (cart == null) {
//			throw new CartNotFoundException("Cart not found for userId : " + userId);
//		}
//
//		Product product = productRepository.findById(productId)
//				.orElseThrow(() -> new ProductsNotFoundException("Product not found with Id : " + productId));
//
//		cart.getProducts().remove(product);
//
//		cartRepository.save(cart);
//
//		return true;
//	}
//
//
//	
