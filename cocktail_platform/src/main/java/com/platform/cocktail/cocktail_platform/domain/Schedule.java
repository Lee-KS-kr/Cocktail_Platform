package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
	int storeCode;
	String times;
	int capacity;
	int reservable;
	int regularHoliday;
	String vacation;
}
