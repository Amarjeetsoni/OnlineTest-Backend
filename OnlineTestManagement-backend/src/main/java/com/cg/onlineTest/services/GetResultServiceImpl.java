package com.cg.onlineTest.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.dao.GetResultDao;
import com.cg.onlineTest.entities.Test;

@Transactional
@Service("GetResultServiceImpl")


public class GetResultServiceImpl implements GetResultService {

	@Autowired
	private GetResultDao resultDao;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestServiceImpl.class);

	/*
	 * isUserExist method is used to call dao layer and also used to check whether user is exist or not.
	 */
	@Override
	public boolean isUserExist(long userId) {
		logger.info("isUserExist service method accessed.");
		return resultDao.isUserExist(userId);
	}

	/*
	 * getUpcomingTest method is used to call dao layer and used to get all upcoming test assign a particular user.
	 */
	@Override
	public List<Test> getUpcomingTest(long userId) throws Exception {
		logger.info("getUpcomingTest service method accessed.");
		return resultDao.getUpcomingTest(userId);
	}

	/*
	 * getActiveTest method is used to call dao layer and used to get a details of test which is active now.
	 */
	@Override
	public Test getActiveTest(long userId) throws Exception {
		logger.info("getActiveTest service method accessed.");
		return resultDao.getActiveTest(userId);
	}

	/*
	 * getAssignedTest method is used to call dao layer and used to get all test assign a particular user.
	 */
	@Override
	public List<Test> getAssignedTest(long userId) throws Exception {
		logger.info("getAssignedTest service method accessed.");
		return resultDao.getAssignedTest(userId);
	}

	@Override
	public boolean assignTest(long testId, long userId) throws Exception {
		logger.info("getAssignedTest service method accessed.");
		return resultDao.assignTest(testId, userId);
	}


}