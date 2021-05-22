package com.cg.eshop.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.eshop.dto.ErrorMessage;
import com.cg.eshop.exception.CategoryNameNotFoundException;
import com.cg.eshop.exception.CategoryNotFoundException;

@RestControllerAdvice
public class CategoryAdvice {
	@ExceptionHandler(CategoryNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCategoryNotFound(CategoryNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(CategoryNameNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionCategoryNameNotFound(CategoryNameNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
}
