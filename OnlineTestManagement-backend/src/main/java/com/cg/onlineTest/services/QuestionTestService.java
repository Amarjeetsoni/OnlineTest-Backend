package com.cg.onlineTest.services;

import java.util.List;

import com.cg.onlineTest.entities.Question;

public interface QuestionTestService {
	public Question addQuestion(Question ques);
	public List<Question> retrieveAllQuestion();
	public String deleteQuestion(int questionId);
	public String updateQuestion(Question question);

}
