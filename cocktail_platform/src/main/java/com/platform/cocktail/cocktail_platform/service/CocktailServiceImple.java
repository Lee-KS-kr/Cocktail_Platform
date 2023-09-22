package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.config.CodeConfig;
import com.platform.cocktail.cocktail_platform.dao.CocktailDAO;
import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Ingredients;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.domain.Taste;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CocktailServiceImple implements CocktailService {
	@Autowired
	CocktailDAO dao;
	
	@Autowired
	CodeConfig cc;

	@Override
	public Cocktails findCocktailByCode(int cocktailCode) {
		Cocktails cocktail = dao.findCocktailByCode(cocktailCode);
		changeIntToString(cocktail);
		getIngredients(cocktail);
		return cocktail;
	}
	
	public Cocktails getIngredients(Cocktails c) {
		if(c.getCocktailBase() != 0)
			c.setCBase(dao.getIngredientNameByCode(c.getCocktailBase()));
		if(c.getIngredient1() != 0)
			c.setCIngre1(dao.getIngredientNameByCode(c.getIngredient1()));
		if(c.getIngredient1() != 0)
			c.setCIngre2(dao.getIngredientNameByCode(c.getIngredient2()));
		if(c.getIngredient1() != 0)
			c.setCIngre3(dao.getIngredientNameByCode(c.getIngredient3()));
		if(c.getIngredient1() != 0)
			c.setCIngre4(dao.getIngredientNameByCode(c.getIngredient4()));
		
		log.debug("{}", c);
		return c;
	}
	
	public Cocktails changeIntToString(Cocktails c) {
		HashMap<String, Integer> codeMap = cc.codeMap();
		String taste = codeMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), c.getCocktailTaste()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0);
		String flavor = codeMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), c.getCocktailFlavor()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0);
		String color = codeMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), c.getCocktailColor()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0);
		
		c.setTaste(taste.split(","));
		c.setFlavor(flavor.split(","));
		c.setColor(color.split(","));
		
		return c;
	}
	
	public Cocktails changeStringToInt(Cocktails c) {
		String taste = StringUtils.join(c.getTaste());
		String flavor = StringUtils.join(c.getFlavor());
		String color = StringUtils.join(c.getColor());
		log.debug("taste : {}, flavor : {}, color : {}", taste, flavor, color);
		
		HashMap<String, Integer> codeMap = cc.codeMap();
		c.setCocktailTaste(codeMap.get(taste));
		c.setCocktailFlavor(codeMap.get(flavor));
		c.setCocktailColor(codeMap.get(color));
		log.debug("cocktailtaste : {}, cocktailflavor : {}, cocktailcolor : {}", 
				c.getCocktailTaste(), c.getCocktailFlavor(), c.getCocktailColor());
		
		return c;
	}

	@Override
	public ArrayList<StoreInfo> searchStore(String searchWord) {
		ArrayList<StoreInfo> list = dao.searchStore(searchWord);
		log.debug("{}", list);
		return list;
	}

	@Override
	public ArrayList<Cocktails> searchCocktail(String searchWord) {
		ArrayList<Cocktails> list = dao.searchCocktail(searchWord);
		for (Cocktails c : list) {
			changeIntToString(c);
			getIngredients(c);
		}
		log.debug("{}", list);
		return list;
	}

	@Override
	public ArrayList<Ingredients> searchIngredient(String searchWord) {
		ArrayList<Ingredients> list = dao.searchIngredient(searchWord);
		log.debug("{}", list);
		return list;
	}
}
