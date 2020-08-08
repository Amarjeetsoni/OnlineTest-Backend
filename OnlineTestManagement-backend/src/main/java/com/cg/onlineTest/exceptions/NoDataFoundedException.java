package com.cg.onlineTest.exceptions;

public class NoDataFoundedException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public NoDataFoundedException() {
		super();
	}
	
	public NoDataFoundedException(String msg) {
		super(msg);
	}

}
