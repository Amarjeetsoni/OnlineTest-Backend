package com.cg.onlineTest.dao;


import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;



@Repository
public class QuestionTestDaoImpl implements QuestionTestDao {

	@PersistenceContext
	EntityManager entityManager;
	
	

	/*
	 Method - addQuestion
     Description - To Add Question into the Question Table in database.
	 @param from addQuestion   - Question Object Containing Question Details.
	 @returns Boolean          - true
	 @throws AddQuestionrException  - It is raised if Question Details are not given properly. 
     Created By                -k Sai Deepika                         
	 */
	@Override
	public Boolean addQuestion(Question question, long cat_id) {	
		Category category = entityManager.find(Category.class, cat_id);
		category.addQuestion(question);
		entityManager.persist(category);
		entityManager.persist(question);
		return true;
		
	}


	/*
	 Method - deleteQuestion
    Description - To Delete Question from Question Table in database.
	 @param from deleteQuestion   - questionId of the question.
    Created By                - K Sai Deepika                           
	 */
	
	@Override
	public Boolean deleteQuestion(Long questionId) {
		Question question=entityManager.find(Question.class, questionId);
		entityManager.remove(question);
		return true;
	}

	/*
	  Method - getAllQuestions
    Description - To fetch all Question Details from the Question Table in database.
    @param from deleteQuestion   - questionId of the question. 
    Created By                   - K Sai Deepika                          
	 */
	
	
	@Override
	public List<Question> getAllQuestions() {
		String qStr = "SELECT q from Question q";
		TypedQuery<Question> query = entityManager.createQuery(qStr, Question.class);
		List<Question> list=query.getResultList();
		return list;
	}
	
	/*
	 Method - addQuestion
    Description - To Add Question into the Question Table in database.
	 @param from addQuestion   - Question Object Containing Question Details.
	 @returns Boolean          - true
	 @throws AddQuestionrException  - It is raised if Question Details are not given properly. 
    Created By                -k Sai Deepika                         
	 */

	@Override
	public Boolean addCategory(Category category) {
		entityManager.persist(category);
		return true;
	}

	/*
	  Method - getCategory
  Description - To fetch category from the Category Table in database.
   @param from categoryId   - categoryId of the Category.
  Created By                   - K Sai Deepika                          
	 */
	
	@Override
	public Category getCategory(Long categoryId) {
		Category category = entityManager.find(Category.class, categoryId);
		return category;
	}
	
	
	
	/*
	 * 	 Method - updateQuestion
     Description - To Update or modify Question in Question Table in database.
	 @param from updateQuestion   - questionId of the question.
	 @returns List<Question>      - returns true if Question gets updated Successfully. 
	 @throws QuestionrException  - It is raised if question details are not given properly. 
     Created By                -K Sai Deepika
	 */
	@Override
	public boolean updateQuestion(long questionId, String questionTitle, List<String> option, int questionAnswer,
			long questionMarks,Category category) {
		
		Question question =  entityManager.find(Question.class,questionId);
		question.setQuestionMarks(questionMarks);
		question.setQuestionTitle(questionTitle);
		question.setQuestionAnswer(questionAnswer);
		question.setQuestionOptions(option);
		question.setQuestionCategory(category);
		entityManager.merge(question);
		return true;
	}

}






