package com.cg.onlineTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.services.ResultService;

@SpringBootApplication
@RestController
@CrossOrigin("*")
public class ResultController {

	@Autowired
	ResultService service;
	
	@GetMapping("/getResult/{userId}")
	public ResponseEntity<Object> getUser(@PathVariable Long userId){
		   
		System.out.println(userId);
		return new ResponseEntity<Object>(service.getResult(userId), HttpStatus.OK);
//		try {
//			System.out.println("This Works..");
//			return new ResponseEntity<Object>(service.getResult(userId), HttpStatus.OK);
//		}
//		catch(Exception exception) {
//			return new ResponseEntity<Object>("Not Founded...", HttpStatus.BAD_GATEWAY);
//		}
	}
	
}
