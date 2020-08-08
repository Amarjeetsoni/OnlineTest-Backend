package com.cg.onlineTest.services;

import java.util.List;

import com.cg.onlineTest.entities.User;

public interface TopPerformerService {
	
	//list of top performers
		List<User> topPerformers();
		
	//number of users
		int getTotalUsers();
		
	//number of active users
		int getActiveUsers();

}
