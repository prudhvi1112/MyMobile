package com.mymobile.repository;

import com.mymobile.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogoutRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserName(String username);
    void save(int id);
}

