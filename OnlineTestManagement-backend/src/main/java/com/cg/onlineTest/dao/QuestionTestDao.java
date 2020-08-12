package com.cg.onlineTest.dao;

import java.util.List;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;

public interface QuestionTestDao {

	Boolean addQuestion(Question question, long cat_id);

	
	Boolean deleteQuestion(Long questionId);
	
	List<Question> getAllQuestions();
	
	Boolean addCategory(Category category);
	
	Category getCategory(Long categoryId);

	boolean updateQuestion(long QuestionId, String questionTitle, int questionAnswer, long questionMarks);
}