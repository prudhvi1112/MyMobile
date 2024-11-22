package com.mymobile.exception;




public class PasswordIncorrectException   extends RuntimeException{

    private String message="password incorrect";

   public PasswordIncorrectException(String message){
        super();
      this.message=message;
   }



}
