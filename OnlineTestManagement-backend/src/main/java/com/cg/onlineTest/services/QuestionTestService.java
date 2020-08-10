package com.cg.onlineTest.services;

import java.util.List;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;

public interface QuestionTestService {
	public Boolean addQuestion(Question ques, long cat_id);
	public List<Question> retrieveAllQuestion();
	public Boolean deleteQuestion(Long questionId);
	public Boolean updateQuestion(Question question);
	void populateQuestionsData();
	public boolean addCategory(Category category);

}
