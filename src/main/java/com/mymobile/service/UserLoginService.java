package com.mymobile.service;

import com.mymobile.dto.LoginRequest;
import com.mymobile.dto.LoginResponse;
import com.mymobile.entity.UserData;
import com.mymobile.exception.InvaildUserException;
import com.mymobile.exception.InvalidUserNameOrPasswordException;
import com.mymobile.repo.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserLoginService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public LoginResponse loginCheck(LoginRequest request) {

		UserData userData = userDetailsDao.findById(request.getUserId())
				.orElseThrow(() -> new InvaildUserException("No User Found With Id : " + request.getUserId()));

		if (userData.getUserId().equals(request.getUserId())
				&& encoder.matches(request.getUserPassword(), userData.getUserPassword())) {
			LoginResponse response = new LoginResponse();
			response.setUserId(userData.getUserId());
			response.setUserRole(userData.getUserRole());
			response.setUserName(userData.getUserName());
			response.setLastLoginTime(userData.getUserLastLoginIn());
			response.setLoginTime(LocalDateTime.now());
			return response;

		} else {

			throw new InvalidUserNameOrPasswordException("Invalid UserName Or Password");

		}

	}

}
