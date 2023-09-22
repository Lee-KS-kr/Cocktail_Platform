package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.RecommendDAO;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Taste;

@Service
public class RecommendServiceImple implements RecommendService {
	@Autowired
	private RecommendDAO dao;

	@Override
	public ArrayList<Menu> getTop3Cocktails() {
		ArrayList<Menu> list = dao.getTop3Cocktails();
		return list;
	}

	@Override
	public ArrayList<Menu> CustomizedRecommendation(Taste t) {
		ArrayList<Menu> list = dao.CustomizedRecommendation();
		return null;
	}
}
