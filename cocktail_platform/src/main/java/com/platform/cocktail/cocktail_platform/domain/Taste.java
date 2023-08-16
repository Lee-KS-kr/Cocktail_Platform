package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taste {
	int tasteCode;
	String memberId;
	int hasAllergy;
	String allergyType;
	int cocktailTaste;
	int cocktailFlavor;
}
