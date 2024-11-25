package com.mymobile.exception;

public class ProductOutOfStockException extends RuntimeException {

	public ProductOutOfStockException() {
		super();

	}

	public ProductOutOfStockException(String message) {
		super(message);

	}

}
