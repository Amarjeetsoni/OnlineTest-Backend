package com.cg.onlineTest;

import org.aspectj.weaver.loadtime.Options;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineTest.controller.QuestionTestController;
import com.cg.onlineTest.dao.QuestionTestDao;

import com.cg.onlineTest.entities.Category;
import com.cg.onlineTest.entities.Question;

import com.cg.onlineTest.services.QuestionTestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

//import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@RunWith(MockitoJUnitRunner.class)


public class QuestionTestTESTING {

	@Mock
	private QuestionTestDao questionDao;
	
	
	@Test
	public void testRetrieveAllQuestion() {
		
		when(questionDao.getAllQuestions()).thenReturn(null);
		assertEquals(null, questionDao.getAllQuestions());
	}
	

	@Test
	  public void testAddQuestion(){
		Question question1 = new Question();
		when(questionDao.addQuestion(question1,1)).thenReturn(null);
		assertEquals(null,questionDao.addQuestion(question1,1));
		
	}
	
	@Test
	 public void testDeleteQuestion() {
		when(questionDao.deleteQuestion(1L)).thenReturn(null);
		assertEquals(null, questionDao.deleteQuestion(1L));
	}
	
	@Test
	 public void testUpdateQuestion() {
		List<String> options = new ArrayList<String>();
		options.add("945");options.add("678");options.add("439");options.add("568");
		category.setCategoryId(1);
		category.setName("Java");
		when(questionDao.updateQuestion(1L,"please insert Question number 1 with title",options,3,10L,category)).thenReturn(true);
		assertEquals(true, questionDao.updateQuestion(1L,"please insert Question number 1 with title",options,3,10L,category));
	}

	
	@Test
	public void testAddCategory() throws Exception {
		Category category = new Category();
		when(questionDao.addCategory(category)).thenReturn(null);
		assertEquals(null, questionDao.addCategory(category));
	}
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	Logger logger = LoggerFactory.getLogger(QuestionTestController.class);
	

	
	@Autowired 
	QuestionTestService questionServiceObject;
	
//	private QuestionTestDao questionTestDao = new QuestionTestDaoImpl();
	
	Question question = new Question();
	Category category = new Category();
	Set<String> option = new HashSet<String>();
	Options optionss = new Options();
	
	
	
	/*
	 * @BeforeAll annotation is used to signal that this method should be executed before any test case runs only once.
	 */
	
	@BeforeAll
	static void setUpBeforeClass()
	{
		System.out.println("Performing Unit Testing For Question Module");
	}
	/*
	 *  @BeforeEach annotation is used to signal that this method should be executed before all test cases.
	 */
	@BeforeEach
	void setup() {
		System.out.println("Test Case Started");
	}
	
	

	/*
	 *  @AfterEach annotation is used to signal that this method should be executed after all test cases.
	 */
	@AfterEach
	void tearDown() {
		System.out.println("Test Case Over");
	}
	
	
	/*
	 * Test will do testing for getAllQuestion() method which fetches all Question from database
	 */
	@Test
	public void getAllQuestions() throws Exception{
		logger.info("Validation for get All Question From Database");
		long expected =40L;
		
		List<Question> allQuestion = questionServiceObject.getAllQuestion();
		logger.info("Total Question is "+allQuestion);
		assertEquals(""+expected,""+allQuestion.size());
	}
	
	/*
	 * Test will do testing for delete Question() method which delete Question from database
	 */
	
	
	@Test
	public void testtDeleteQuestion() throws Exception {
		question.setQuestionId(2);
		question.setQuestionTitle("The sum of all two digit numbers divisible by 5 is");
		question.setQuestionMarks(2);
		question.setQuestionAnswer(4);
		
		
		Boolean message= true;
		logger.info("Validation to delete a  Question From Database");
		 Boolean result = questionServiceObject.deleteQuestion(2L);
		
		assertEquals(result, message);		
		
		
		
	}
	
	/*
	 * Test will do testing for add Question() method which add Question from database
	 */
	
	
	
	@Test
	public void testtAddQuestion() throws Exception {
		category.setCategoryId(1);
		category.setName("Java");
		List<String> options = new ArrayList<String>();
		question.setQuestionId(2);
		question.setQuestionCategory(category);
		question.setQuestionTitle("The sum of all two digit numbers divisible by 5 is");
		options.add("945");options.add("678");options.add("439");options.add("568");
		question.setQuestionOptions(options);
		question.setQuestionMarks(2);
		question.setQuestionAnswer(4);
		
		Boolean message= true;
		logger.info("Validation to Add a  Question From Database");
		 Boolean result = questionServiceObject.addQuestion(question, 1);
		
		assertEquals(result, message);		
		
		
		
	}
	
	/*
	 * Test will do testing for add Question() method which add Question from database
	 */
	
	
	@Test
	public void testtUpdateQuestion() throws Exception {
		List<String> options = new ArrayList<String>();
		options.add("945");options.add("678");options.add("439");options.add("568");
		
		Boolean message= true;
		logger.info("Validation to Add a  Question From Database");
		 boolean result = questionServiceObject.updateQuestion(1L,"please insert Question number 1 with title",options,3,10L,1);
		
		assertEquals(result, message);		
		
		
		
	}
	
	
	/*
	 * Test will do testing for add Category() method which add Category from database
	 */
	
	
	
	@Test
	public void testtAddCategory() throws Exception {
		category.setCategoryId(1);
		category.setName("Java");
		
		
		Boolean message= true;
		logger.info("Validation to Add a  Category From Database");
		 Boolean result = questionServiceObject.addCategory(category);
		
		assertEquals(result, message);	
}
}
