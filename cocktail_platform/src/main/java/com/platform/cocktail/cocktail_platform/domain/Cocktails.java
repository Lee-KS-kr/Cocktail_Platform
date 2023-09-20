package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cocktails {
	int cocktailCode;
	String cocktailName;
	String cocktailAlcohol;
	String savedFilename;

	int cocktailTaste;
	int cocktailFlavor;
	int cocktailColor;
	String[] taste;
	String[] flavor;
	String[] color;

	int cocktailBase;
	int ingredient1;
	int ingredient2;
	int ingredient3;
	int ingredient4;
	String cBase;
	String cIngre1;
	String cIngre2;
	String cIngre3;
	String cIngre4;
}
