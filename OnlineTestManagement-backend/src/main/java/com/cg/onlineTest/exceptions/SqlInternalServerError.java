package com.cg.onlineTest.exceptions;

public class SqlInternalServerError extends Exception{

	private static final long serialVersionUID = 1L;
	
	public SqlInternalServerError() {
		super();
	}
	
	public SqlInternalServerError(String msg) {
		super(msg);
	}
	

}
