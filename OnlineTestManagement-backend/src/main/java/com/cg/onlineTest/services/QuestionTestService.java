package com.cg.onlineTest.services;

import java.util.List;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;

public interface QuestionTestService {
	public Boolean addQuestion(Question ques, long cat_id);
	public List<Question> getAllQuestion();
	public Boolean deleteQuestion(Long questionId);
	public boolean addCategory(Category category);
	public boolean updateQuestion(long questionId, String questionTitle, int questionAnswer, long questionMarks);

}