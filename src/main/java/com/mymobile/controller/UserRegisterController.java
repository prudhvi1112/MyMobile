package com.mymobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymobile.dto.UserAddedResponse;
import com.mymobile.dto.UserDataDto;
import com.mymobile.service.UserRegisterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
@CrossOrigin("*")
public class UserRegisterController {
	@Autowired
	private UserRegisterService userRegisterService;

	@PostMapping("/")
	public ResponseEntity<UserAddedResponse> addUser(@Valid @RequestBody UserDataDto userDataDto) {
		return new ResponseEntity<>(userRegisterService.addUser(userDataDto), HttpStatus.CREATED);
	}

}