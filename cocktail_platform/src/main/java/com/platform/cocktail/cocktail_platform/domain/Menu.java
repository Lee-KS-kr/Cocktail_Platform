package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	int menuNum;
	int storeCode;
	String menuName;
	int price;
	Category data;
	String menuInfo;
	String originFilename;
	String savedFilename;
	boolean canOrder;
	String storeName;
}
