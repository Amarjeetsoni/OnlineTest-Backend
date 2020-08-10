package com.cg.onlineTest.dao;

import java.util.List;

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
	
	
	
	@Override
	public Boolean addQuestion(Question question) {
		entityManager.persist(question);
		return true;
	}

	@Override
	public Boolean updateQuestion(Question question) {
		entityManager.merge(question);
		return true;
	}

	@Override
	public Boolean deleteQuestion(Long questionId) {
		Question question=entityManager.find(Question.class, questionId);
		entityManager.remove(question);
		return true;
	}

	@Override
	public List<Question> getAllQuestions() {
		String qStr = "SELECT q from Question q";
		TypedQuery<Question> query = entityManager.createQuery(qStr, Question.class);
		List<Question> list=query.getResultList();
		System.out.println("Working");
		return list;
	}

	@Override
	public Boolean addCategory(Category category) {
		entityManager.persist(category);
		return true;
	}

	@Override
	public Category getCategory(Long categoryId) {
		Category category = entityManager.find(Category.class, categoryId);
		return category;
	}

}
