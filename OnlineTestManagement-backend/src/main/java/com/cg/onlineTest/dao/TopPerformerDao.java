package com.cg.onlineTest.dao;

import java.util.HashMap;
import java.util.List;


import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.CannotRetrieveDataException;
import com.cg.onlineTest.exceptions.NoDataFoundedException;

public interface TopPerformerDao {
	// list of top performers
		List<User_Test> topPerformers() throws Exception;

	//number of users
		long getTotalUsers() throws CannotRetrieveDataException;

	//number of test
		long getTotalTests() throws CannotRetrieveDataException;
		
	//number of questions
		long getTotalQuestions() throws CannotRetrieveDataException;
		
	//list of exams
		List<User_Test> allExams() throws Exception;
		
	//number of question in each Category
		HashMap<String, Long> questionsCategory() throws NoDataFoundedException ;
		
	//top 3 students by average marks
		List<User_Test> topPerformersAvg() throws Exception;
}