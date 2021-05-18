package com.cg.eshop.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.eshop.dto.ErrorMessage;
import com.cg.eshop.exception.BasketException;
import com.cg.eshop.exception.CustomerNotFoundException;
import com.cg.eshop.exception.ProductNotFoundException;

public class BasketAdvice {
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionProductNotFound(ProductNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCustomerNotFound(CustomerNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	

	@ExceptionHandler(BasketException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionBasketException(BasketException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

}
