package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTemp {
	int tempOrderkey;
	String orderCode;
	int menuNum;
	String menuName;
	int pricePerOne;
	int orderCount;
	OrderState state;
}
