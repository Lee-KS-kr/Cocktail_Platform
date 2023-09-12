package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CocktailDAO {

	ArrayList<Object> search(HashMap<String, String> map);

}
