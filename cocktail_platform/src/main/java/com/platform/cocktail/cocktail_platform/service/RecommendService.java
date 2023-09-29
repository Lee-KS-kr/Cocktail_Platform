package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import com.platform.cocktail.cocktail_platform.domain.Cocktails;
import com.platform.cocktail.cocktail_platform.domain.Taste;

public interface RecommendService {

	ArrayList<Cocktails> getTop3Cocktails();

	ArrayList<Cocktails> CustomizedRecommendation(Taste t);

	ArrayList<Cocktails> recommendForMe(String username);

	ArrayList<Cocktails> weatherRecommend(String filter);

	ArrayList<Cocktails> ageRecommend(String filter);

	ArrayList<Cocktails> companionRecommend(String filter);

	ArrayList<Cocktails> eventRecommend(String filter);
}
