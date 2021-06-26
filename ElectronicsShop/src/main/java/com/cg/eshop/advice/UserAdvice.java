package com.cg.eshop.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.cg.eshop.dto.ErrorMessage;
import com.cg.eshop.exception.*;
import com.cg.eshop.utils.LoginConstants;

@RestControllerAdvice
public class UserAdvice {
	
//	@ExceptionHandler(UserNotFoundException.class)
//	@ResponseStatus(code = HttpStatus.NOT_FOUND)
//	public ErrorMessage handleExceptionUserNotFound(UserNotFoundException ex) {
//		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
//	}
//	
	@ExceptionHandler(LoginException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ErrorMessage handleLoginException(LoginException ex) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
	}
	
	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleHeaderException(MissingRequestHeaderException ex)	{
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), LoginConstants.MISSING_REQUEST_HEADER);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleExceptionForDate(MethodArgumentTypeMismatchException ex)	{
		if(ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), LoginConstants.INVALID_DATE_PATTTERN);
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), LoginConstants.MUST_BE_NUMERIC);
	}
	
	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException3(HttpMessageConversionException ex)	{
		if(ex.getMessage().contains("LocalDate"))
			return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(),  LoginConstants.INVALID_DATE_PATTTERN+", follow yyyy-MM-dd");
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), LoginConstants.NUMERIC_DATE);
	}
	
	@ExceptionHandler(ValidateUserException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleException2(ValidateUserException ex) {
		List<String> errors=ex.getErrors().stream()
				.map(err->err.getDefaultMessage()).collect(Collectors.toList());
		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(), errors);
	}
	
//	@ExceptionHandler(AlreadyExists.class)
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	public ErrorMessage handleAlreadyExistsException(AlreadyExists ex) {
//		return new ErrorMessage(HttpStatus.BAD_REQUEST.toString(),ex.getMessage());
//	}

}
