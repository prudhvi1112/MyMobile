package com.mymobile.exception;

public class UserIdIncorrectException extends RuntimeException{

    private String message="userId incorrect";

    public UserIdIncorrectException(String message){
      super();
     this.message =message;
    }
}
