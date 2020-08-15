package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineTest.controller.OnlineTestController;
import com.cg.onlineTest.dao.ResultDao;
import com.cg.onlineTest.dao.ResultDaoImpl;
import com.cg.onlineTest.entities.CategoryResult;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.exceptions.DataMismatchExcpetion;
import com.cg.onlineTest.services.ResultService;



@SpringBootTest
@Transactional
public class ResultAnalysisJunit {
	
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);
	
	@Autowired
	private ResultService resultService;
	
	
	
	
	private ResultDao resultDao = new ResultDaoImpl();
	
	@BeforeAll
	static void setUpBeforeClass() {
		System.out.println("Perfoming Unit Testing for ResultAnalysis Module");
	}
	
	
	/*
	 * This test will do testing for getResult() method which fetches the Result of user ateempted test
	 */
	@Test
	public void getResultTest() throws Exception {
		logger.info("Validation of Get All Results of a particular user");
		Long expected = 1L;
		List<User_Test> questionsList = resultService.getResult(expected);
		Long actual = questionsList.get(0).getUser().getUserId();
		logger.info("Actual Value is :" + actual);
		assertEquals(""+expected,""+ actual);
	}

	
	/*
	 * This test will do testing for getCategoryResult() method which fetches the category wise Result of user ateempted test.
	 */
	@Test
	public void getCategoryResult() throws Exception {
		logger.info("Validation of Get All category wise Results of a particular user");
		Long expected = 92L;
		List<CategoryResult> questionsList = resultService.getCategoryResult(expected);
		Long actual = questionsList.get(0).getUserTest().getUser_Test_Id();
		logger.info("Actual Value is :" + actual);
		assertEquals(""+expected,""+ actual);
	}
	
	
	/*
	 * This test will do testing for DateMismatchException.
	 */
	
	@org.junit.Test(expected = DataMismatchExcpetion.class)
	public void exceptionCheckForGetResult() throws Exception {
		
		
		List<User_Test> resultList = null;
		logger.info("Validation of DataMismatchException");
		Long expected = 91L;
		if(resultDao != null)
		{
			resultList = resultDao.getResult(expected);
		}
		logger.info("Actual Value is :" + resultList);
	}
	
	
	/*
	 * This test will do testing for fetching questions from database.
	 */
	@Test
	public void getQuestionsTest() throws Exception{
		
		logger.info("Validation of Get All Questions of a particular test");
		Long testId = 1L;
		List<Question> questionsList = resultService.getQuestions(testId);
		
		assertEquals(true,questionsList.size() > 0);
		
	}
	
}
