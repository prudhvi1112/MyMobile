package com.mymobile.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mymobile.dto.UserAddedResponse;
import com.mymobile.dto.UserDataDto;
import com.mymobile.entity.UserData;
import com.mymobile.exception.PasswordAndConfirmPasswordException;
import com.mymobile.exception.UserIdOrEmailAlreadyExistsException;
import com.mymobile.repo.UserDetailsDao;

@Service
public class UserRegisterService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public UserAddedResponse addUser(UserDataDto userDataDto) {
		
		if(userDataDto.getUserPassword().equals(userDataDto.getUserConfirmPassword())) {
		UserData userData = mapper.map(userDataDto, UserData.class);
		UserData userEmail = userDetailsDao.findByUserEmail(userData.getUserEmail());
		
		
		UserData userDetails = userDetailsDao.findById(userData.getUserId()).orElse(null);
		System.out.println(userDetails);
		if (userEmail == null && userDetails == null) {
			
			userData.setUserPassword(encoder.encode(userDataDto.getUserPassword()));
			userData.setUserConfirmPassword(encoder.encode(userDataDto.getUserConfirmPassword()));
			
			
			userData.setUserRegsiterDate(LocalDateTime.now());
			userData.setUserLastLoginIn(LocalDateTime.now());
			userDetailsDao.save(userData);
			UserAddedResponse response = new UserAddedResponse(userData.getUserId(), userData.getUserEmail());
			return response;
		} else {
			throw new UserIdOrEmailAlreadyExistsException("User Id Or Email Already Exist");
		}
		}
		else
		{
			throw new PasswordAndConfirmPasswordException("Password And Confirm Must Be Same");
		}
	}

}
