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
	QuestionTestDao dao;
	
//	public QuestionTestServiceImpl() {
//		populateQuestionsData();
//	}
//	
	@Override
	public List<Question> retrieveAllQuestion() {
		// TODO Auto-generated method stub
		return dao.getAllQuestions();
	}

	@Override
	public Boolean deleteQuestion(Long questionId) {
		// TODO Auto-generated method stub
		return dao.deleteQuestion(questionId);
	}

	@Override
	public Boolean updateQuestion(Question question) {
		
		return dao.updateQuestion(question);
	}

	@Override
	public Boolean addQuestion(Question question, long cat_id) {
		
		return dao.addQuestion(question, cat_id);
	}

//	@Override
//	public void populateQuestionsData()
//	{
//		Category category = new Category();
////		category.setCategoryId(1);
////		category.setName("Core Java");
////		dao.addCategory(category);
//		category.setCategoryId(2);
//		category.setName("Spring");
//		dao.addCategory(category);
////		category.setCategoryId(3);
////		category.setName("SQL");
////		dao.addCategory(category);
////		category.setCategoryId(4);
////		category.setName("JPA Hibernate");
////		dao.addCategory(category);
////		
//		Set<String> options = null;
//		
//		Question question = new Question();
//		question.setQuestionId(2);
//		question.setQuestionCategory(dao.getCategory((long)1));
//		question.setQuestionTitle("The sum of all two digit numbers divisible by 5 is");
//		options.add("945");options.add("678");options.add("439");options.add("568");
//		question.setQuestionOptions(options);
//		question.setQuestionMarks(2);
//		question.setQuestionAnswer(4);
//		dao.addQuestion(question);
//		options.clear();
//		
//		
////		use the same way to create 40 questions.. or 80 your wish, 4 categories are being used 
////		take care of question Id ... no need to create any more objects
//	}

	@Override
	public boolean addCategory(Category category) {
		return dao.addCategory(category);
	}

	@Override
	public void populateQuestionsData() {
		// TODO Auto-generated method stub
		
	}
}
