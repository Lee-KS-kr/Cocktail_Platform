package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Taste;

public interface RecommendService {

	ArrayList<Menu> getTop3Cocktails();

	ArrayList<Menu> CustomizedRecommendation(Taste t);

}
