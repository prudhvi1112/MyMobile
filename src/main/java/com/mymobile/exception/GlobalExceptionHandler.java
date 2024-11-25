package com.mymobile.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ResponseEntity<String> handleProductAlreadyExists(ProductAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductsNotFoundException.class)
	public ResponseEntity<String> handleProductsNotFound(ProductsNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PasswordAndConfirmPasswordException.class)
	public ResponseEntity<String> handlePasswordAndConfirmPassword(PasswordAndConfirmPasswordException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidUserNameOrPasswordException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUserNameOrPassword(InvalidUserNameOrPasswordException ex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", "Invalid UserId Or Password");
		map.put("userPassword", "Invalid UserId Or Password");
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvaildUserException.class)
	public ResponseEntity<String> handleInvaildUserException(InvaildUserException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvaildUserToAddProductException.class)
	public ResponseEntity<String> handleInvaildUserToAddProduct(InvaildUserToAddProductException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserIdOrEmailAlreadyExistsException.class)
	public ResponseEntity<String> handleUserIdOrEmailAlreadyExists(UserIdOrEmailAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		// Loop through all the field errors and add them to the error map
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		// Return the response entity with the validation error messages
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
//		Map<String, String> errors = new HashMap<>();
//
//		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//			String propertyPath = violation.getPropertyPath().toString();
//			String errorMessage = violation.getMessage();
//			errors.put(propertyPath, errorMessage);
//		}
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex) {
		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}
