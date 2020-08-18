package com.cg.onlineTest.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cg.onlineTest.dao.OnlineTestDao;
import com.cg.onlineTest.entities.FeedBack;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;

@Transactional
@Service("OnlineTestServiceImpl")
public class OnlineTestServiceImpl implements OnlineTestService{
	
	/*
	 * @Autowired is used to
	 * Inject the dependency of OnlineTestDao class into OnlineTestServiceImpl class.
	 * It internally uses setter or constructor to inject dependency.  
	 */
	@Autowired
	private OnlineTestDao testDao;
	
	/*
	 * Logger used to Record unusual circumstances or error that may be happening in the program.
	 * also use to get info what is going in the application.
	 */
	Logger logger = LoggerFactory.getLogger(OnlineTestServiceImpl.class);
	
	
	/*
	 * getAllTestAssignToPerticularUser method is used to call dao layer and used to get all test assign a particular user.
	 */
	@Override
	public List<Test> getAllTestAssignToPerticularUser(long userId) throws Exception{
		logger.info("getAllTestAssignToPerticularUser service method accessed.");
		return testDao.getAllTestAssignToPerticularUser(userId);
	}

	/*
	 * getAllUpcomingTest method is used to call dao layer and used to get all upcoming test assign a particular user.
	 */
	@Override
	public List<Test> getAllTest() throws Exception{
		logger.info("getAllUpcomingTest service method accessed.");
		return testDao.getAllTest();
	}

	/*
	 * getActiveTest method is used to call dao layer and used to get a details of test which is active now.
	 */
	@Override
	public Test getActiveTest(long userId, long testId) throws Exception{
		logger.info("getActiveTest service method accessed.");
		return testDao.getActiveTest(userId, testId);
	}

	/*
	 * getAllQuestion method is used to call dao layer and also used to get all Question assigned to a particular test.
	 */
	@Override
	public List<Question> getAllQuestion(long userId, long testId) throws Exception {
		logger.info("getAllQuestion service method accessed.");
		return testDao.getAllQuestion(userId, testId);
	}

	
	/*
	 * getAllFeedback method is used to call dao layer and also used to get all feedback of particular test.
	 */
	@Override
	public List<FeedBack> getAllFeedback(long testId) throws Exception{
		logger.info("getAllFeedback service method accessed.");
		return testDao.getAllFeedback(testId);
	}

	/*
	 * isUserExist method is used to call dao layer and also used to check whether user is exist or not.
	 */
	@Override
	public boolean isUserExist(long userId) {
		logger.info("isUserExist service method accessed.");
		return testDao.isUserExist(userId);
	}
     
	/*
	 * addFeedback method is used to call dao layer and also used add a feedback to a test.
	 */
	@Override
	public boolean addFeedback(List<String> feedback, long testId, long userId) throws Exception {
		logger.info("addFeedback service method accessed.");
		return testDao.addFeedback(feedback, testId, userId);
	}

	/*
	 * submitTest method is used to call dao layer and also used to pass information to dao layer.
	 */	
	@Override
	public boolean submitTest(List<Integer> answer, long testId, long userId) throws Exception {
		logger.info("submitTest service method accessed.");
		return testDao.submitTest(answer, testId, userId);
	}


}
