package com.cg.onlineTest.services;

import java.util.List;

import com.cg.onlineTest.entities.CategoryResult;

import com.cg.onlineTest.entities.User_Test;

public interface ResultService {

	List<User_Test> getResult(Long userId) throws Exception;
	
	List<CategoryResult> getCategoryResult(Long userTestId) throws Exception;
}
