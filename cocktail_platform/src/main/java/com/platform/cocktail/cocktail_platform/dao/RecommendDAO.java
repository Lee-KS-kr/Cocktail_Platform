package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Taste;

@Mapper
public interface RecommendDAO {

	ArrayList<Menu> getTop3Cocktails();

	ArrayList<Menu> CustomizedRecommendation(Taste t);

	ArrayList<Menu> weatherRecommend(String filter);

	ArrayList<Menu> ageRecommend(String filter);

	ArrayList<Menu> companionRecommend(String filter);

	ArrayList<Menu> eventRecommend(String filter);

}
