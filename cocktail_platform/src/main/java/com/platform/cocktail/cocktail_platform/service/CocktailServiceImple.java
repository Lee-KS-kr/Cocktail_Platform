package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.CocktailDAO;
import com.platform.cocktail.cocktail_platform.domain.Cocktails;

@Service
public class CocktailServiceImple implements CocktailService {
	@Autowired
	CocktailDAO dao;

	@Override
	public ArrayList<Object> search(String category, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("searchWord", searchWord);
		
		ArrayList<Object> list = dao.search(map);
		return list;
	}

	@Override
	public Cocktails findCocktailByCode(int cocktailCode) {
		Cocktails cocktail = dao.findCocktailByCode(cocktailCode);
		return cocktail;
	}
}
