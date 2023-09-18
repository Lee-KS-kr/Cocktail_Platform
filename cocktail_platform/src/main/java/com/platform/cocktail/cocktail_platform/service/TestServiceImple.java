package com.platform.cocktail.cocktail_platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.TestDAO;
import com.platform.cocktail.cocktail_platform.domain.Order;

@Service
public class TestServiceImple implements TestService {
	@Autowired
	TestDAO dao;

	@Override
	public int inputOrderTest(Order o) {
		int n = dao.inputOrderTest(o);
		return n;
	}
	
}
