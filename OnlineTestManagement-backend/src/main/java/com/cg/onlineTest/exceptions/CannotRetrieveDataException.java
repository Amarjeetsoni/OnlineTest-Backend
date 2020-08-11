package com.cg.onlineTest.exceptions;

public class CannotRetrieveDataException extends Exception{

	private static final long serialVersionUID = 1L;

	public CannotRetrieveDataException() {
		super();
	}

	public CannotRetrieveDataException(String message) {
		super(message);
	}
	
}
