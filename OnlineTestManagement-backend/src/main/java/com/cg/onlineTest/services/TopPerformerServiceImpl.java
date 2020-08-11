package com.cg.onlineTest.services;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.dao.TopPerformerDao;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.CannotRetrieveDataException;
import com.cg.onlineTest.exceptions.NoDataFoundedException;

@Transactional
@Service("TopPerformerServiceImpl")
public class TopPerformerServiceImpl implements TopPerformerService {
	
	@Autowired
	TopPerformerDao topPerformersDao;
	
	Logger logger = LoggerFactory.getLogger(TopPerformerDao.class);
	
	@Override
	public List<User_Test> topPerformers() throws Exception {
		logger.info("Top performers function is accessed!");
		return topPerformersDao.topPerformers();
	}

	@Override
	public long getTotalUsers() throws CannotRetrieveDataException {
		logger.info("get Total Users function is accessed!");
		return topPerformersDao.getTotalUsers();
	}

	@Override
	public long getTotalTests() throws CannotRetrieveDataException {
		logger.info("get Total Test function is accessed!");
		return topPerformersDao.getTotalTests();
	}

	@Override
	public long getTotalQuestions() throws CannotRetrieveDataException {
		logger.info("get Total Questions function is accessed!");
		return topPerformersDao.getTotalQuestions();
	}

	@Override
	public List<User_Test> allExams() throws Exception {
		logger.info("all Exam function is accessed!");
		return topPerformersDao.allExams();
	}

	@Override
	public HashMap<String, Long> questionsCategory() throws NoDataFoundedException {
		logger.info("Question category function is accessed!");
		return topPerformersDao.questionsCategory();
	}

}
