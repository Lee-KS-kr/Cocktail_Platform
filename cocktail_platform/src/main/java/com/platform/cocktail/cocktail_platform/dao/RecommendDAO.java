package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Menu;

@Mapper
public interface RecommendDAO {

	ArrayList<Menu> getTop3Cocktails();

	ArrayList<Menu> CustomizedRecommendation();

	ArrayList<Menu> weatherRecommend();

	ArrayList<Menu> ageRecommend();

	ArrayList<Menu> companionRecommend();

	ArrayList<Menu> eventRecommend();

}
