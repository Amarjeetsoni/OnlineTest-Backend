package com.cg.onlineTest.services;


import java.util.List;

import org.springframework.util.MultiValueMap;

import com.cg.onlineTest.entities.CategoryResult;

import com.cg.onlineTest.entities.User_Test;

public interface CalculateScoreService {
	
	//List<Integer> fetchOptions(User_Test userTest);
	Long calculateScoreService(Long userTestId) throws Exception;
	List<CategoryResult> categoryScore(Long userTestId) throws Exception;
	List<User_Test> getTests() throws Exception;
	
}
