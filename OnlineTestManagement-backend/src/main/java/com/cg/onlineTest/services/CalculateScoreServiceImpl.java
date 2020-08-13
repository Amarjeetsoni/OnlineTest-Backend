package com.cg.onlineTest.services;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineTest.controller.OnlineTestController;
import com.cg.onlineTest.dao.CalculateScoreDao;
import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.CategoryResult;
import com.cg.onlineTest.entities.Question;

import com.cg.onlineTest.entities.User_Test;

@Service
public class CalculateScoreServiceImpl implements CalculateScoreService {
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);
	@Autowired
	private CalculateScoreDao calculateDao;
	
	
	
	/*
	 * calculateScoreService method is used to calculate the score by comparing the answers list
	 * and list of options selected by the user in a Test.
	 * And also updates the result of the comparision into a Boolean List for each question.
	 * 
	 *  Returns Long Integer of score calculated for the Test.
	 */
	@Override
	public Long calculateScoreService(Long userTestId) throws Exception {
		
		Long score=0L;
		
		User_Test userTest =  calculateDao.getUserTest(userTestId);

		List<Boolean> answersCorrectedList = userTest.getTestCorrectAnswer();
		List<Integer> optionList = userTest.getUsertestAnswer();
		List<Question> questionsList = userTest.getTest().getAllQuestion();
		
		int currentQuestion = 0;
		for(Integer option: optionList) 
		{
			if(option == 0) 
			{
				answersCorrectedList.add(null);
			}
			else if(option == questionsList.get(currentQuestion).getQuestionAnswer()) 
			{
				answersCorrectedList.add(true);
				Long marks = questionsList.get(currentQuestion).getQuestionMarks();
				score += marks;
			}
			else 
			{
				answersCorrectedList.add(false);
				
			}
			currentQuestion++;
			
		}
		
		userTest.setMarksScored(score);
		userTest.setTestCorrectAnswer(answersCorrectedList);
		
		calculateDao.setScore(userTest);
		logger.info("Method executed to calculate score ->" + score);
		System.out.println(score);
		categoryScore(userTest);
		return score;
	}


	/*
	 * categoryScore method is used to calculate the score category wise
	 * 
	 * And also updates the result in Category_Result table.
	 * 
	 *  Returns List if Category_Result.
	 */
	public List<CategoryResult> categoryScore(User_Test userTest) throws Exception{
		List<Question> questionsList = userTest.getTest().getAllQuestion();
		List<Boolean> answeredCorrectList = userTest.getTestCorrectAnswer();
		
		Set<Category> categorySet  = new HashSet<Category>();
		for(Question question: questionsList)
		{
			Category category = question.getQuestionCategory();
			categorySet.add(category);
		}
		
	
		int answerListIteration = 0;
		Long attemptedQuestions = 0L;
		Long categoryScore = 0L;
		
		List<CategoryResult> categoryResultList = new ArrayList<CategoryResult>();
		
		for(Category category : categorySet)
		{
			CategoryResult categoryResult = new CategoryResult();
			answerListIteration=0;
			attemptedQuestions = 0L;
			categoryScore = 0L;
			for(Question question: questionsList)
			{
				if(category.getCategoryId()  == question.getQuestionCategory().getCategoryId()) {
					if(answeredCorrectList.get(answerListIteration) == true)
					{
						attemptedQuestions++;
						categoryScore += question.getQuestionMarks(); 
					}
					else if(answeredCorrectList.get(answerListIteration) == false)
					{
						attemptedQuestions++;
					}
				}
				answerListIteration++;
			}
			categoryResult.setAttemptedQuestions(attemptedQuestions);
			categoryResult.setCategoryResult(categoryScore);
			categoryResult.setCategory(category);
			categoryResult.setUserTest(userTest);
			
			calculateDao.setCategoryResult(categoryResult);
			
			categoryResultList.add(categoryResult);
			
			logger.info("Method executed for Category wise score");
		}
		return categoryResultList;
	}

		
	


}
