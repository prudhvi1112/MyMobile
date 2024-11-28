package com.mymobile.controller;

import com.mymobile.dto.LogoutResponse;
import com.mymobile.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
@CrossOrigin("*")
public class LogoutController {

	@Autowired
	private LogoutService logoutService;

	@PutMapping("/{userId}")
	public ResponseEntity<LogoutResponse> logout(@PathVariable String userId) {
		return new ResponseEntity<>(logoutService.logoutUser(userId), HttpStatus.OK);
	}
}
