package com.cg.onlineTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.cg.onlineTest.entities.Question;
import com.cg.onlineTest.services.QuestionTestService;

@SpringBootTest
class QuestionTestTESTING {

	//QuestionDao dao;
		@Mock
		QuestionTestService dao;
		
		
	   //Deepika
		@Test
		void testretrieveAllQuestion() {
			
			when(dao.retrieveAllQuestion()).thenReturn(null);
			assertEquals(null, dao.retrieveAllQuestion());
		}
		
		@Test
		 void testaddQuestion() throws Exception{
			Question question1 = new Question();
			when(dao.addQuestion(question1)).thenReturn(null);
			assertEquals(null, dao.addQuestion(question1));
			
		}
		
		@Test
		void testdeleteQuestion() throws Exception {
			when(dao.deleteQuestion(1L)).thenReturn(null);
			assertEquals(null, dao.deleteQuestion(1L));
		}
		
		@Test
		void testupdateQuestion() throws Exception {
			Question question1 = new Question();
			when(dao.updateQuestion(question1)).thenReturn(null);
			assertEquals(null, dao.updateQuestion(question1));
		}

}
