package com.mymobile.controller;

import com.mymobile.dto.UserDataDto;
import com.mymobile.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;


   @PostMapping ("/login")
    public ResponseEntity login(@RequestParam String userId,@RequestParam String password){
          return userLoginService.login(userId,password);
    }

}
