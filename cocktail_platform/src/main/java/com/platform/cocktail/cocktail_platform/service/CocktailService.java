package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;

public interface CocktailService {

	ArrayList<Object> search(String category, String searchWord);

	Cocktails findCocktailByCode(int cocktailCode);

}
