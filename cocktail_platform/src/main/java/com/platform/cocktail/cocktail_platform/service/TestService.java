package com.platform.cocktail.cocktail_platform.service;

import java.io.IOException;

import com.platform.cocktail.cocktail_platform.domain.Order;

public interface TestService {

	int inputOrderTest(Order o);

	void insertMenus(String filepath) throws IOException;

	void deleteMenus();

	void insertCodes();

	void deleteCodes();

}
