package com.cg.onlineTest.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.exceptions.EmailExistsException;
import com.cg.onlineTest.exceptions.UserNameExistsException;


@Service
public interface IUserService extends UserDetailsService {

	public User findById(Integer id);
	public void createUser(User user);
	public void signUp(User cust) throws EmailExistsException,UserNameExistsException;
	public User getUserByName(String username);
	public List<User> getAllCustomers();
	public List<User> getAllAdmins();
	public void createAdmin(User admin);
	public Long getUserId(String username);
	
}
