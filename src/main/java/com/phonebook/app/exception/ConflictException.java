package com.phonebook.app.exception;


public class ConflictException extends RuntimeException {

	public ConflictException(String message) {
		super(message);
	}

	public ConflictException() {
		super("a conflict was found");
	}

}
