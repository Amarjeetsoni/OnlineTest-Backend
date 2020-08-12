package com.cg.onlineTest.dao;

import java.util.List;
import java.util.Set;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;

public interface QuestionTestDao {

	Boolean addQuestion(Question question, long cat_id);

	
	Boolean deleteQuestion(Long questionId);
	
	List<Question> getAllQuestions();
	
	Boolean addCategory(Category category);
	
	Category getCategory(Long categoryId);

	boolean updateQuestion(long questionId, String questionTitle, Set<String> option, int questionAnswer,
			long questionMarks, Category category);


	


}
