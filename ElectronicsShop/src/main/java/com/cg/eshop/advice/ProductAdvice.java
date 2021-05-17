package com.cg.eshop.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.eshop.dto.ErrorMessage;
import com.cg.eshop.exception.NoSpecsException;
import com.cg.eshop.exception.ProductNotFoundException;
import com.cg.eshop.exception.ValidateException;

@RestControllerAdvice
public class ProductAdvice {
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionProductNotFound(ProductNotFoundException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionForDate(MethodArgumentTypeMismatchException ex) {
		if (ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must a numeric value");
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException3(HttpMessageConversionException ex) {
		if (ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "Invalid Date Pattern , follow yyyy-M-d");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), "It must be numeric");
	}

	@ExceptionHandler(ValidateException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ErrorMessage handleException2(ValidateException ex) {
		List<String> errors = ex.getErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.FORBIDDEN.toString(), errors);
	}

	@ExceptionHandler(NoSpecsException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ErrorMessage handleExceptionSpecNotFound(NoSpecsException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}

}
