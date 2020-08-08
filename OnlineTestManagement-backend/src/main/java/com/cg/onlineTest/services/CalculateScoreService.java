package com.cg.onlineTest.services;

import java.util.Set;

import com.cg.onlineTest.entities.User_Test;

public interface CalculateScoreService {
	
	Set<User_Test> calculateScore(User_Test userTest);
	Boolean registerTest(int userId, int testId);

}
