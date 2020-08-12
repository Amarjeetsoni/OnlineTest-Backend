package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.onlineTest.controller.OnlineTestController;
import com.cg.onlineTest.entities.User_Test;
import com.cg.onlineTest.services.CalculateScoreService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class CalculateScoreTESTING {

	@Autowired
	private CalculateScoreService serv;
	@Autowired
	private EntityManager entityManager;
	
	Logger logger = LoggerFactory.getLogger(OnlineTestController.class);
	
	
	
	@Test
	@DisplayName("Testing of total score for a user in a Test")
	void calculateScoreTest() throws Exception {
		logger.info("Validation of calculating score of a Test");
		
		//Test 1
		Long score = 10L;
		assertEquals(score, serv.calculateScoreService(21L));
		
		//Test 2
		score = 0L;
		assertEquals(score, serv.calculateScoreService(23L));
		
	}

	@Test
	@DisplayName("Testing of category wise score for a user in a Test")
	void calculateCategoryScore() throws Exception {
		logger.info("Validation of category wise score of a Test");
		
		//Test 1
		assertTrue(0< serv.categoryScore(entityManager.find(User_Test.class, 21L)).size());	
		
		//Test 2
		assertTrue(0<serv.categoryScore(entityManager.find(User_Test.class, 23L)).size());
	}

	@Test
	@DisplayName("Testing of score for a particular category")
	void checkScoreInCategory() throws Exception {
		logger.info("Validation of score of a particular category");
		//Test 1
		assertEquals(0L, serv.categoryScore(entityManager.find(User_Test.class, 21L)).get(0).getCategoryResult());	
		
		//Test 2
		assertEquals(0L, serv.categoryScore(entityManager.find(User_Test.class, 21L)).get(3).getCategoryResult());
	}
}
