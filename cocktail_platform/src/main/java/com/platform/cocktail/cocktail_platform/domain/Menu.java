package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
	int menuNum;
	int storeCode;
	String menuName;
	int price;
	Category category;
	String menuInfo;
	String originFilename;
	String savedFilename;
	boolean canOrder;
	String storeName;
}
