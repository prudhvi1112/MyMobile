package com.mymobile.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymobile.dto.ProductAddedResponse;
import com.mymobile.dto.ProductDto;
import com.mymobile.dto.ProductsDto;
import com.mymobile.entity.Product;
import com.mymobile.entity.UserData;
import com.mymobile.exception.InvaildUserException;
import com.mymobile.exception.InvaildUserToAddProductException;
import com.mymobile.exception.ProductAlreadyExistsException;
import com.mymobile.exception.UserNotFoundException;
import com.mymobile.repo.ProductDao;
import com.mymobile.repo.UserDetailsDao;

@Service
public class VendorService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private ModelMapper mapper;

	@Transactional
	public ProductAddedResponse createProduct(ProductDto productDto) {

		UserData userData = userDetailsDao.findById(productDto.getVendorId())
				.orElseThrow(() -> new UserNotFoundException("Vendor Not Found With Id : " + productDto.getVendorId()));
		if (userData.getUserRole().equalsIgnoreCase("vendor")) {
			Product product = mapper.map(productDto, Product.class);
			Product findProduct = productDao.findById(product.getProductId()).orElse(null);
			if (findProduct == null) {

				product.setUserData(userData);
				productDao.save(product);

				List<Product> productList = userData.getProducts();
				if (productList.contains(product)) {
					throw new ProductAlreadyExistsException("Product Already Exists");
				} else {
					productList.add(product);
					userData.setProducts(productList);
					userDetailsDao.save(userData);
					ProductAddedResponse response = new ProductAddedResponse();
					response.setProductId(product.getProductId());
					response.setSuccessMessage("Product Added Successfully");
					return response;
				}
			} else {
				throw new ProductAlreadyExistsException("Product Already Exists");
			}
		} else {
			throw new InvaildUserToAddProductException("Invalid User To Add Products");
		}
	}

	public List<ProductsDto> getProductsList(String vendorId) {
		if (vendorId == null) {
			List<Product> productList = productDao.findAll();
			return productList.stream().map(product -> mapper.map(product, ProductsDto.class))
					.collect(Collectors.toList());
		} else {
			List<Product> productListByVendoeId = productDao.findByUserData_UserId(vendorId);
			if (productListByVendoeId.isEmpty()) {
				throw new InvaildUserException("Invalid user or no products included.");
			}
			return productListByVendoeId.stream().map(product -> mapper.map(product, ProductsDto.class))
					.collect(Collectors.toList());
		}

	}
}
