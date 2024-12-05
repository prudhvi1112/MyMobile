package com.mymobile.exception;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleProductAlreadyExists(ProductAlreadyExistsException ex) {
		Map<String, String> error = new HashMap<>();
		error.put("productId", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductsNotFoundException.class)
	public ResponseEntity<Response<String>> handleProductsNotFound(ProductsNotFoundException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.NOT_FOUND.name());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<Response<String>> handleCartNotFound(CartNotFoundException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.NOT_FOUND.name());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PasswordAndConfirmPasswordException.class)
	public ResponseEntity<Response<String>> handlePasswordAndConfirmPassword(PasswordAndConfirmPasswordException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.NOT_FOUND.name());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidUserNameOrPasswordException.class)
	public ResponseEntity<Map<String, String>> handleInvalidUserNameOrPassword(InvalidUserNameOrPasswordException ex) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", "Invalid UserId Or Password");
		map.put("userPassword", "Invalid UserId Or Password");

		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvaildUserException.class)
	public ResponseEntity<Map<String, String>> handleInvaildUserException(InvaildUserException ex) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", ex.getMessage());

		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response<String>> handleUserNotFound(UserNotFoundException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.NOT_FOUND.name());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserIdOrEmailAlreadyExistsException.class)
	public ResponseEntity<Map<String, String>> handleUserIdOrEmailAlreadyExists(
			UserIdOrEmailAlreadyExistsException ex) {

		return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvaildUserToAddProductException.class)
	public ResponseEntity<Response<String>> handleInvaildUserToAddProduct(InvaildUserToAddProductException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductOutOfStockException.class)
	public ResponseEntity<Response<String>> handleProductOutOfStock(ProductOutOfStockException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidCheckOutException.class)
	public ResponseEntity<Response<String>> handleInvalidCheckOut(InvalidCheckOutException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BarcodeNotFoundException.class)
	public ResponseEntity<Response<String>> handleBarcodeNotFound(InvalidCheckOutException ex) {
		Response<String> response = new Response<>(ex.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<String>> handleGenericException(Exception ex) {
		Response<String> response = new Response<>("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.name());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
