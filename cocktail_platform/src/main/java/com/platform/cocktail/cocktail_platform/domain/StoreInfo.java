package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreInfo {
	int storeCode;
	String memberId;
	String storeName;
	String address;
	String phone;
	String runningTime;
	String dayOff;
	String info;
}
