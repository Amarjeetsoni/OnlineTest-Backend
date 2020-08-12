package com.cg.onlineTest.services;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.dao.QuestionTestDao;
import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;


@Service
@Transactional
public class QuestionTestServiceImpl implements QuestionTestService {

	@Autowired
	QuestionTestDao questionDao;
	
//	public QuestionTestServiceImpl() {
//		populateQuestionsData();
//	}
//	
	
	/*
	  Method - getAllQuestions
  Description - To fetch all Question Details from the Question Table in database.
  @param from deleteQuestion   - questionId of the question. 
  Created By                   - K Sai Deepika                          
	 */
	
	@Override
	public List<Question> getAllQuestion() {
		// TODO Auto-generated method stub
		return questionDao.getAllQuestions();
	}

	
	/*
	 Method - deleteQuestion
   Description - To Delete Question from Question Table in database.
	 @param from deleteQuestion   - questionId of the question.
   Created By                - K Sai Deepika                           
	 */
	
	
	@Override
	public Boolean deleteQuestion(Long questionId) {
		// TODO Auto-generated method stub
		 questionDao.deleteQuestion(questionId);
		 return true;
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
	public boolean updateQuestion(long questionId, String questionTitle, int questionAnswer, long questionMarks) {
		questionDao.updateQuestion(questionId,questionTitle,questionAnswer,questionMarks);
		return true; 
		
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
	public Boolean addQuestion(Question question, long cat_id) {
		
		return questionDao.addQuestion(question, cat_id);
	}

	
	/*
	  Method - getCategory
Description - To fetch category from the Category Table in database.
 @param from categoryId   - categoryId of the Category.
Created By                   - K Sai Deepika                          
	 */
	
	@Override
	public boolean addCategory(Category category) {
		return questionDao.addCategory(category);
	}


}