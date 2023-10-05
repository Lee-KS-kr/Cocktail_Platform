package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	String orderCode;
	int storeCode;
	String orderDate;
	String memberId;
	int totalPrice;
	String menuList;
	OrderState orderState;
	String storeName;
	boolean isReviewDone;
}
