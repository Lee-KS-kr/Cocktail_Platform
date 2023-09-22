package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Ingredients;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;

public interface CocktailService {
	
	Cocktails findCocktailByCode(int cocktailCode);

	ArrayList<StoreInfo> searchStore(String searchWord);

	ArrayList<Cocktails> searchCocktail(String searchWord);

	ArrayList<Ingredients> searchIngredient(String searchWord);

}
