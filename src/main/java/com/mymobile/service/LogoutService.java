package com.mymobile.service;

import com.mymobile.dto.LogoutResponse;
import com.mymobile.entity.UserData;
import com.mymobile.exception.InvaildUserException;
import com.mymobile.repo.UserDetailsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class LogoutService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	public LogoutResponse logoutUser(String userId) {

		UserData user = userDetailsDao.findById(userId)
				.orElseThrow(() -> new InvaildUserException("User Not Found With Id : " + userId));

		LocalDateTime localDateTime = LocalDateTime.now();
		user.setUserLastLoginIn(localDateTime);
		userDetailsDao.save(user);
		LogoutResponse response = new LogoutResponse();
		response.setUserId(userId);
		response.setLogoutTime(localDateTime);
		return response;

	}
}
