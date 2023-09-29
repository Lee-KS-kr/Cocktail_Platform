package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.config.CodeConfig;
import com.platform.cocktail.cocktail_platform.dao.RecommendDAO;
import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Taste;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecommendServiceImple implements RecommendService {
	@Autowired
	private RecommendDAO dao;
	
	@Autowired
	CodeConfig cc;
	
	@Override
	public ArrayList<Cocktails> getTop3Cocktails() {
		log.debug("top3");
		ArrayList<Cocktails> list = dao.getTop3Cocktails();
		list = getCocktailDetails(list);
		log.debug("best 3 {}", list);
		return list;
	}

	@Override
	public ArrayList<Cocktails> CustomizedRecommendation(Taste t) {
		log.debug("customize");
		ArrayList<Cocktails> list = dao.CustomizedRecommendation(t);
		list = getCocktailDetails(list);
		log.debug("customize {}", list);
		return list;
	}

	@Override
	public ArrayList<Cocktails> weatherRecommend(String filter) {
		log.debug("weather");
		int newFilter = convertStringToInt(filter);
		ArrayList<Cocktails> list = dao.weatherRecommend(newFilter);
		list = getCocktailDetails(list);
		log.debug("weather {} {}", filter, list);
		return list;
	}

	@Override
	public ArrayList<Cocktails> ageRecommend(String filter) {
		int newFilter = convertStringToInt(filter);
		ArrayList<Cocktails> list = dao.ageRecommend(newFilter);
		list = getCocktailDetails(list);
		log.debug("age {} {}", filter, list);
		return list;
	}

	@Override
	public ArrayList<Cocktails> companionRecommend(String filter) {
		log.debug("companion");
		int newFilter = convertStringToInt(filter);
		ArrayList<Cocktails> list = dao.companionRecommend(newFilter);
		list = getCocktailDetails(list);
		log.debug("companion {} {}", filter, list);
		return list;
	}

	@Override
	public ArrayList<Cocktails> eventRecommend(String filter) {
		log.debug("event");
		int newFilter = convertStringToInt(filter);
		ArrayList<Cocktails> list = dao.eventRecommend(newFilter);
		list = getCocktailDetails(list);
		log.debug("event {} {}", filter, list);
		return list;
	}
	
	public int convertStringToInt(String before) {
		if(before.isEmpty())
			return 0;
		
		HashMap<String, Integer> codeMap = cc.codeMap();
		return codeMap.get(before);
	}
	
	public ArrayList<Cocktails> getCocktailDetails(ArrayList<Cocktails> list){
		ArrayList<Cocktails> newList = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			Cocktails c = dao.getCocktailByName(list.get(i).getCocktailName());
			newList.add(c);
		}
		return newList;
	}
}
