package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;

@Mapper
public interface CocktailDAO {

	ArrayList<Object> search(HashMap<String, String> map);

	Cocktails findCocktailByCode(int cocktailCode);

	String getIngredientNameByCode(int cocktailBase);

}
