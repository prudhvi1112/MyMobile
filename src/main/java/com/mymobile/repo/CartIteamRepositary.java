package com.mymobile.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymobile.entity.CartIteam;

@Repository
public interface CartIteamRepositary extends JpaRepository<CartIteam, Long> {
	Optional<List<CartIteam>> findByCartUserIdAndProductId(String userId, String productId);

	List<CartIteam> findByCartUserId(String userId);

}
