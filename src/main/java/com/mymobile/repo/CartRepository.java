package com.mymobile.repo;

import com.mymobile.entity.Cart;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
	Cart findByUserId(String userId);

}
