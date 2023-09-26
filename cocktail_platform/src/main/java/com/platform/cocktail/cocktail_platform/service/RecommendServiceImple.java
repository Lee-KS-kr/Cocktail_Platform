package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.config.CodeConfig;
import com.platform.cocktail.cocktail_platform.dao.RecommendDAO;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Taste;

@Service
public class RecommendServiceImple implements RecommendService {
	@Autowired
	private RecommendDAO dao;
	
	@Autowired
	CodeConfig cc;
	
	@Override
	public ArrayList<Menu> getTop3Cocktails() {
		ArrayList<Menu> list = dao.getTop3Cocktails();
		return list;
	}

	@Override
	public ArrayList<Menu> CustomizedRecommendation(Taste t) {
		ArrayList<Menu> list = dao.CustomizedRecommendation(t);
		return list;
	}

	@Override
	public ArrayList<Menu> weatherRecommend(String filter) {
		ArrayList<Menu> list = dao.weatherRecommend(filter);
		return list;
	}

	@Override
	public ArrayList<Menu> ageRecommend(String filter) {
		ArrayList<Menu> list = dao.ageRecommend(filter);
		return list;
	}

	@Override
	public ArrayList<Menu> companionRecommend(String filter) {
		ArrayList<Menu> list = dao.companionRecommend(filter);
		return list;
	}

	@Override
	public ArrayList<Menu> eventRecommend(String filter) {
		ArrayList<Menu> list = dao.eventRecommend(filter);
		return list;
	}
	
	public int convertStringToInt(String before) {
		HashMap<String, Integer> codeMap = cc.codeMap();
		return codeMap.get(before);
	}
}
