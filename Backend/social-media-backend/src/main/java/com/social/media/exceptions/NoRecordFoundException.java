package com.social.media.exceptions;

@SuppressWarnings("serial")
public class NoRecordFoundException extends Exception {

	/**
	 * No argument constructor.
	 */
	public NoRecordFoundException() {
	}

	/**
	 * 
	 * @param message String object which contains appropriate message.
	 */
	public NoRecordFoundException(String message) {
		super(message);
	}

}