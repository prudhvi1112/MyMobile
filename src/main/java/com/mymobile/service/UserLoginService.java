package com.mymobile.service;


import com.mymobile.dto.UserDataDto;
import com.mymobile.entity.UserData;
import com.mymobile.exception.PasswordIncorrectException;
import com.mymobile.exception.UserIdOrEmailAlreadyExistsException;
import com.mymobile.repo.UserDetailsDao;
import com.mymobile.util.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserLoginService {
    @Autowired
    private UserDetailsDao userDetailsDao;



    public ResponseEntity login(String userId, String userPassword){

        Optional<UserData> db= userDetailsDao.findById(userId);

        if(db!=null){
            String  encodedInputPassword = Base64.getEncoder().encodeToString(userPassword.getBytes());
            if(db.get().getUserPassword().equals(encodedInputPassword)){
                UserLoginResponse responseDTO = new UserLoginResponse();
                responseDTO.setUserId(db.get().getUserId());
                responseDTO.setUserRegsiterDate(db.get().getUserRegsiterDate());
                responseDTO.setUserRegsiterDate(db.get().getUserLastLoginIn());
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
            throw new PasswordIncorrectException("password incorrect");
        }
        throw new UserIdOrEmailAlreadyExistsException("user incorrect");
    }
}
