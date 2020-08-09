package com.cg.onlineTest.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;

@Repository
public class ResultDaoImpl implements ResultDao {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public List<User_Test> getResult(Long userId) {
	
		User user = entityManager.find(User.class,userId);
		String qStr = "SELECT ut from User_Test ut where ut.user=:user and ut.isAttempted = 1";
		TypedQuery<User_Test> query = entityManager.createQuery(qStr, User_Test.class);
		query.setParameter("user", user);
		List<User_Test> list=query.getResultList();
		
		return list;
	}

}
