package com.mymobile.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymobile.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, String> {

	List<Product> findByUserData_UserId(String vendorId);
	 Product findByProductIdAndUserDataUserId(String productId, String userId);

}
