package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Taste;

@Mapper
public interface RecommendDAO {

	ArrayList<Cocktails> getTop3Cocktails();

	ArrayList<Cocktails> CustomizedRecommendation(Taste t);
	
	ArrayList<Cocktails> recommendForMe(String memberId);

	ArrayList<Cocktails> weatherRecommend(String filter);

	ArrayList<Cocktails> ageRecommend(String filter);

	ArrayList<Cocktails> companionRecommend(String filter);

	ArrayList<Cocktails> eventRecommend(String filter);

	Cocktails getCocktailByName(String cocktailName);

}
