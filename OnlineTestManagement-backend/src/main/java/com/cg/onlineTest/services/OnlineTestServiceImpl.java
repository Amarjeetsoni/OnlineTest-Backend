package com.cg.onlineTest.services;

import java.util.List;


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
	
	@Autowired
	private OnlineTestDao testDao;

	@Override
	public List<Test> getAllTestAssignToPerticularUser(long userId) throws Exception{
		return testDao.getAllTestAssignToPerticularUser(userId);
	}

	@Override
	public List<Test> getAllUpcomingTest(long userId) throws Exception{
		return testDao.getAllUpcomingTest(userId);
	}

	@Override
	public Test getActiveTest(long userId) throws Exception{
		return testDao.getActiveTest(userId);
	}

	@Override
	public List<Question> getAllQuestion(long testId) throws Exception {
		return testDao.getAllQuestion(testId);
	}

	

	@Override
	public List<FeedBack> getAllFeedback(long testId) throws Exception{
		return testDao.getAllFeedback(testId);
	}

	@Override
	public boolean isUserExist(long userId) {
		return testDao.isUserExist(userId);
	}

	@Override
	public boolean addFeedback(List<String> feedback, long testId, long userId) throws Exception {
		return testDao.addFeedback(feedback, testId, userId);
	}

	@Override
	public boolean submitTest(List<Integer> answer, long testId, long userId) throws Exception {
		return testDao.submitTest(answer, testId, userId);
	}


}
