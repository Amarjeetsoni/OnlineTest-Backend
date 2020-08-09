package com.cg.onlineTest.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineTest.dao.ResultDao;
import com.cg.onlineTest.entities.User;
import com.cg.onlineTest.entities.User_Test;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDao dao;
	
	@Override
	public List<User_Test> getResult(Long userId) {
		
		return dao.getResult(userId);
	}

}
