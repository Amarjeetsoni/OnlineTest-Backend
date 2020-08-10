package com.cg.onlineTest.dao;

import java.util.List;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;

public interface QuestionTestDao {

	Boolean addQuestion(Question question);
	
	Boolean updateQuestion(Question question);
	
	Boolean deleteQuestion(Long questionId);
	
	List<Question> getAllQuestions();
	
	Boolean addCategory(Category category);
	
	Category getCategory(Long categoryId);
}
