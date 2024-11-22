package com.mymobile.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerException {

    @ExceptionHandler(UserIdIncorrectException.class)
    public ResponseEntity<String> handleEmailNotCorrectException(UserIdIncorrectException ex){
        return new ResponseEntity<>("userId is incorrect", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<String>handlePasswordNotCorrectException(PasswordIncorrectException ex){
        return new ResponseEntity<>("wrong password",HttpStatus.NOT_FOUND);
    }
     @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
          return new ResponseEntity<>("UserId Already Exists",HttpStatus.CONFLICT);
    }

}
