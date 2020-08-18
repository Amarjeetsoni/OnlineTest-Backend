package com.cg.onlineTest.dao;

import java.util.List;

import com.cg.onlineTest.entities.FeedBack;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.Test;

public interface OnlineTestDao {
	public boolean isUserExist(long userId);
	public List<Test> getAllTestAssignToPerticularUser(long UserId) throws Exception;
	public List<Test> getAllTest() throws Exception;
	public Test getActiveTest(long userId, long testId) throws Exception;
	public List<Question> getAllQuestion(long UserId, long testId) throws Exception;
	public boolean addFeedback(List<String> feedback, long testId, long userId) throws Exception;
	public List<FeedBack> getAllFeedback(long testId) throws Exception;
	public boolean submitTest(List<Integer> answer, long testId, long userId) throws Exception;
}
