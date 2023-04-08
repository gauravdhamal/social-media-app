package com.social.media.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 
	 * @param noRecordFoundException : Object of noRecordFoundException contains all
	 *                               information about noRecordFoundException.
	 * @param webRequest             : Object of WebRequest contains all request
	 *                               data from header.
	 * @return : Response Entity along with proper HttpStatus code.
	 */
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<MyErrorDetails> noRecordFoundExceptionHandler(NoRecordFoundException noRecordFoundException,
			WebRequest webRequest) {
		System.out.println("NoRecordFoundException handler.");
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), noRecordFoundException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param methodArgumentNotValidException : Object of
	 *                                        MethodArgumentNotValidException
	 *                                        contains all data of exception.
	 * @return : Response Entity along with proper HttpStatus code.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(
			MethodArgumentNotValidException methodArgumentNotValidException) {
		System.out.println("MethodArgumentNotValidException handler.");
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(),
				methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage(),
				"Validation error");
		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param illegalArgumentException : Object of IllegalArgumentException contains
	 *                                 all data of exception.
	 * @param webRequest               : Object of WebRequest contains all request
	 *                                 data from header.
	 * @return : Response Entity along with proper HttpStatus code.
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MyErrorDetails> illegalArgumentExceptionHandler(
			IllegalArgumentException illegalArgumentException, WebRequest webRequest) {
		System.out.println("IllegalArgumentException handler.");
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), illegalArgumentException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param noHandlerFoundException : Object of NoHandlerFoundException contains
	 *                                all data of exception.
	 * @param webRequest              : Object of WebRequest contains all request
	 *                                data from header.
	 * @return : Response Entity along with proper HttpStatus code.
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(
			NoHandlerFoundException noHandlerFoundException, WebRequest webRequest) {
		System.out.println("NoHandlerFoundException handler.");
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), noHandlerFoundException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param exception  : Object of Exception contains all data of exception.
	 * @param webRequest : Object of WebRequest contains all request data from
	 *                   header.
	 * @return : Response Entity along with proper HttpStatus code.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> exceptionHandler(Exception exception, WebRequest webRequest) {
		System.out.println("Exception handler.");
		MyErrorDetails error = new MyErrorDetails(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}

}