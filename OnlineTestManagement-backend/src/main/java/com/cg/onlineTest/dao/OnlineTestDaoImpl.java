package com.cg.onlineTest.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("OnlineTestDaoImpl")
@Transactional
public class OnlineTestDaoImpl implements OnlineTestDao{
	
	@PersistenceContext
	private EntityManager entityManager;

}
