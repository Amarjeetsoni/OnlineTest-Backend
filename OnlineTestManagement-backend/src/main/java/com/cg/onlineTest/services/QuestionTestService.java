package com.cg.onlineTest.services;

import java.util.List;
import java.util.Set;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;

public interface QuestionTestService {
	public Boolean addQuestion(Question ques, long cat_id);
	public List<Question> getAllQuestion();
	public Boolean deleteQuestion(Long questionId);
	void populateQuestionsData();
	public boolean addCategory(Category category);
	
	boolean updateQuestion(long questionId, String questionTitle, List<String> option, int questionAnswer,
			long questionMarks, long category_id);
	
	
}