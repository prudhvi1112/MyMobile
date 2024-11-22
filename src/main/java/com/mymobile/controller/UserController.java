package com.mymobile.controller;

import com.mymobile.entity.User;
import com.mymobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("userid successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login( @RequestParam String userId,@RequestParam String password ,@RequestBody User user){

        return userService.login(userId,password,user);

    }


}
