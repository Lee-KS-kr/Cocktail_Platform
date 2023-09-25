package com.platform.cocktail.cocktail_platform.service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.cocktail.cocktail_platform.api.csv.ReadLineContext;
import com.platform.cocktail.cocktail_platform.dao.TestDAO;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Order;

@Service
@Transactional
public class TestServiceImple implements TestService {
	@Autowired
	TestDAO dao;
	@Autowired
	ReadLineContext<Menu> csvReader;

	@Override
	public int inputOrderTest(Order o) {
		int n = dao.inputOrderTest(o);
		return n;
	}

	@Override
	public void insertMenus(String filepath) throws IOException {
		ArrayList<Menu> list = csvReader.readByLine(filepath);
		for (Menu m : list) {
			dao.insertMenu(m);
		}
	}

	@Override
	public void deleteMenus() {
		dao.deleteAllMenus();
	}
	
}
