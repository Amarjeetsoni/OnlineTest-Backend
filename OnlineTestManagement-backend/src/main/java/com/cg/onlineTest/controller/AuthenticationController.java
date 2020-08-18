package com.cg.onlineTest.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineTest.OnlineTestManagementBackendApplication;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.services.IUserService;
import com.cg.onlineTest.utils.AuthenticationRequest;
import com.cg.onlineTest.utils.AuthenticationResponse;
import com.cg.onlineTest.utils.JwtUtil;


@CrossOrigin("*")
@RestController
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	IUserService userDetailsService;

	@Autowired
	JwtUtil jwtutil;
	
	Logger loger=LoggerFactory.getLogger(OnlineTestManagementBackendApplication.class);
	String msg;
	
	@PostMapping(value="/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		try
		{
			msg="Authenticating Username and pasword";
			loger.info(msg);
		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		}
		catch(Exception exc)
		{
			System.out.println("--------------------------------------------" + exc);
			throw new Exception("Incorrect UserName Password");
		}
		System.out.println("--------------------------------------------");
		final UserDetails userdetails=userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println("--------------------------------------------");
		final String jwt=jwtutil.generateToken(userdetails);
		System.out.println("--------------------------------------------" + jwt);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
	
	@PostMapping(value="/signup")
	public ResponseEntity<Boolean> createCustomer(@RequestBody userTable customer)
	{
		msg="Customer Registration";
		User user = new User();
		user.setActiveTest(false);
		user.setAdmin(false);
		user.setUserName(customer.username);
		user.setEmailId(customer.email);
		user.setUserPassword(customer.password);
		
		loger.info(msg);
		userDetailsService.signUp(user);
		return ResponseEntity.ok(true);
	}
	
	
	@GetMapping(value="/getAll")
	@ResponseBody
	public ResponseEntity<?> fetchAll()
	{
		msg="fetching all Customers registered";
		loger.info(msg);
		return ResponseEntity.ok(userDetailsService.getAllCustomers());
	}
	
	@GetMapping(value="/getAllAdmins")
	@ResponseBody
	public ResponseEntity<?> fetchAllAdmins()
	{
		msg="fetching all Admins registered";
		loger.info(msg);
		return ResponseEntity.ok(userDetailsService.getAllAdmins());
	}
	
	@GetMapping(value="/getUserId")
	@ResponseBody
	public ResponseEntity<?> getUserId(@RequestBody String username){
		msg = "geting user Id";
		loger.info(msg);
		return ResponseEntity.ok(userDetailsService.getUserId(username));
	}
	
}

class userTable{
	public String username;
	public String email;
	public String password;
}
