package com.cg.onlineTest.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlineTest.dao.ResultDao;
import com.cg.onlineTest.entities.CategoryResult;
import com.cg.onlineTest.entities.User_Test;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDao dao;
	
	@Override
	public List<User_Test> getResult(Long userId) throws Exception {
		
		return dao.getResult(userId);
	}

	@Override
	public List<CategoryResult> getCategoryResult(Long userTestId) throws Exception{
		// TODO Auto-generated method stub
		return dao.getCategoryResult(userTestId);
	}

}
