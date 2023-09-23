package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Ingredients;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;

@Mapper
public interface CocktailDAO {

	Cocktails findCocktailByCode(int cocktailCode);

	String getIngredientNameByCode(int cocktailBase);

	ArrayList<StoreInfo> searchStore(String searchWord);

	ArrayList<Cocktails> searchCocktail(String searchWord);

	ArrayList<Ingredients> searchIngredient(String searchWord);
}
