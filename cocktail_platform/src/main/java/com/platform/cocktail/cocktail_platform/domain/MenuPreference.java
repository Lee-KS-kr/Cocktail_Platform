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
	String memberId;
	int menuNum;
	int weather;
	int ageGroup;
	int companion;
	int event;
}
