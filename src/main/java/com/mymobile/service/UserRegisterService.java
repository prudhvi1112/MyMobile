package com.mymobile.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

		if (userDataDto.getUserPassword().equals(userDataDto.getUserConfirmPassword())) {
			UserData userData = mapper.map(userDataDto, UserData.class);
			UserData userEmail = userDetailsDao.findByUserEmail(userData.getUserEmail());
			if (userDetailsDao.existsByUserEmail(userData.getUserEmail())) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userEmail", "Email Already Exists");
				throw new UserIdOrEmailAlreadyExistsException("Validation Error", map);
			}

			UserData userDetails = userDetailsDao.findById(userData.getUserId()).orElse(null);
			if (userDetailsDao.existsByUserId(userData.getUserId())) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userId", "UserId Already Exists");
				throw new UserIdOrEmailAlreadyExistsException("Validation Error", map);
			}
			if (userEmail == null && userDetails == null) {

				userData.setUserPassword(encoder.encode(userDataDto.getUserPassword()));
				userData.setUserConfirmPassword(encoder.encode(userDataDto.getUserConfirmPassword()));

				userData.setUserRegsiterDate(LocalDateTime.now());
				userData.setUserLastLoginIn(LocalDateTime.now());
				userDetailsDao.save(userData);
				UserAddedResponse response = new UserAddedResponse(userData.getUserId(), userData.getUserEmail());
				return response;
			} else {
				throw new UserIdOrEmailAlreadyExistsException();
			}
		} else {
			throw new PasswordAndConfirmPasswordException("Password And Confirm Must Be Same");
		}
	}

}
