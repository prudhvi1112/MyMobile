package com.mymobile.exception;

public class InvalidUserNameOrPasswordException extends RuntimeException {

	public InvalidUserNameOrPasswordException() {
		super();

	}

	public InvalidUserNameOrPasswordException(String message) {
		super(message);

	}

}
