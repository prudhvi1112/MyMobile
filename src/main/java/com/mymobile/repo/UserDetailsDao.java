package com.mymobile.repo;

import com.mymobile.dto.UserDataDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mymobile.entity.UserData;

public interface UserDetailsDao extends JpaRepository<UserData, String>
{
	
	UserData findByUserEmail(String userEmail);


}
