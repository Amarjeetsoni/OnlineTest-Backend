package com.cg.onlineTest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.onlineTest.exceptions.EmailExistsException;
import com.cg.onlineTest.exceptions.UserNameExistsException;




@RestControllerAdvice
public class UserControllerAdvice {
	
	
	
	
	@ExceptionHandler(EmailExistsException.class)
    public final ResponseEntity<String> exceptionHandler( EmailExistsException e) 
    {
       
     System.out.println();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UserNameExistsException.class)
    public final ResponseEntity<String> exceptionHandler( UserNameExistsException e) 
    {
       
     System.out.println();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	
	
	
	

}
