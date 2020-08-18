package com.cg.onlineTest.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.onlineTest.dao.IUserDao;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.exceptions.EmailExistsException;
import com.cg.onlineTest.exceptions.UserNameExistsException;


@Service
public class UserService implements UserDetailsService,IUserService {
	
	@Autowired
	IUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userDao.getUserByName(username);
		return new UserDetails() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}
			
			@Override
			public String getUsername() {
				return user.getUserName();
			}
			
			@Override
			public String getPassword() {

				return user.getUserPassword();
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {

				return new ArrayList<>();
			}
		};
}

	@Override
	public User findById(Integer id) {

		User user=userDao.findById(id);
		return user;
	}

	@Override
	public void createUser(User user) {
		
		userDao.createUser(user);
		
	}

	@Override
	public void signUp(User cust) throws EmailExistsException,UserNameExistsException {
		int userNameStatus=userDao.userExistsOrNot(cust.getUserName());
		int emailStatus=userDao.SignUpEmailValidate(cust.getEmailId());
		
	    if(emailStatus==1)
		{
			throw new EmailExistsException("Email already used ,try with different Email Id");
		}
	    	
		else if(userNameStatus==1) 
		{
			throw new UserNameExistsException("UserName already taken ,try different UserName"); 
		}
		userDao.signUp(cust);
		
		
	}

	@Override
	public User getUserByName(String username) {
		
		return userDao.getUserByName(username);
	}
	@Override
	public List<User> getAllCustomers() {
		
		return userDao.getAllCustomers();
	}

	@Override
	public void createAdmin(User admin) {
		userDao.createAdmin(admin);
		
		
		
	}

	@Override
	public List<User> getAllAdmins() {
	
		return userDao.getallAdmins();
	}

	@Override
	public Long getUserId(String username) {
		
		return  userDao.getUsrId(username);
	}
}