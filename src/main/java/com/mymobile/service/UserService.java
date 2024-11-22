package com.mymobile.service;

import com.mymobile.entity.User;
import com.mymobile.exception.UserIdIncorrectException;
import com.mymobile.exception.PasswordIncorrectException;
import com.mymobile.repository.UserRepository;
import com.mymobile.util.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> save(User user) {

        LocalDateTime currentTimestamp = LocalDateTime.now();

        // Encrypt password using Base64 encoding
        String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        user.setPassword(encodedPassword);


        user.setLoggedInTimeStamp(currentTimestamp);
        user.setUpdateInTimeStamp(currentTimestamp);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    public ResponseEntity login(String userId,String password,User user){
        LocalDateTime currentTimestamp = LocalDateTime.now();
        User db = userRepository.findByUserId(userId);
        user.setLoggedInTimeStamp(currentTimestamp);
        user.setUpdateInTimeStamp(currentTimestamp);
        if(db!=null){
          String  encodedInputPassword = Base64.getEncoder().encodeToString(password.getBytes());
            if(db.getPassword().equals(encodedInputPassword)){
                UserResponse responseDTO = new UserResponse();
                responseDTO.setUserId(db.getUserId());
                responseDTO.setLoggedInTimeStamp(db.getLoggedInTimeStamp());
                responseDTO.setUpdateInTimeStamp(db.getUpdateInTimeStamp());
               return new ResponseEntity<>(responseDTO,HttpStatus.OK);
            }
            throw new PasswordIncorrectException("password incorrect");
        }
        throw new UserIdIncorrectException("user incorrect");
    }



}