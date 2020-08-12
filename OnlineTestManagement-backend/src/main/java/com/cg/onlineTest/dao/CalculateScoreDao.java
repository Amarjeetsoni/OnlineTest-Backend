package com.cg.onlineTest.dao;


import com.cg.onlineTest.entities.CategoryResult;
import com.cg.onlineTest.entities.User_Test;

public interface CalculateScoreDao {
	
	User_Test getUserTest(long userTestId) throws Exception;

	boolean setScore(User_Test userTest) throws Exception;
		
	void setCategoryResult(CategoryResult categoryResult) throws Exception;
}
