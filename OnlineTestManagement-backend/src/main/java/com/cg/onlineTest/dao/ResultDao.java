package com.cg.onlineTest.dao;

import java.util.List;

import com.cg.onlineTest.entities.CategoryResult;

import com.cg.onlineTest.entities.User_Test;

public interface ResultDao {

	List<User_Test> getResult(Long userId) throws Exception;
	
	List<CategoryResult> getCategoryResult(Long userTestId) throws Exception;
	
}
