package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuPreference {
	int menuPreferenceNum;
	int menuNum;
	String weather;
	String ageGroup;
	String companion;
	String event;
}
