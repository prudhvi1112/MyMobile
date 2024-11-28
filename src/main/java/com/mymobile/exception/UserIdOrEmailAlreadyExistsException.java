package com.mymobile.exception;

import java.util.Map;

public class UserIdOrEmailAlreadyExistsException extends RuntimeException {
	private Map<String, String> errors;

	public UserIdOrEmailAlreadyExistsException() {
		super();
	}

	public UserIdOrEmailAlreadyExistsException(String message, Map<String, String> errors) {
		super(message);
		this.errors = errors;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

}
