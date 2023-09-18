package com.platform.cocktail.cocktail_platform.dao;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Order;

@Mapper
public interface TestDAO {

	int inputOrderTest(Order o);

}
