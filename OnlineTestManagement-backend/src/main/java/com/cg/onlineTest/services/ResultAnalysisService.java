package com.cg.onlineTest.services;

import java.util.HashMap;
import java.util.Set;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;

public interface ResultAnalysisService {

	Set<User_Test> getResult(User user);
	
	HashMap<String, Integer> getCategoryAnalysis(User_Test userTest);

}
