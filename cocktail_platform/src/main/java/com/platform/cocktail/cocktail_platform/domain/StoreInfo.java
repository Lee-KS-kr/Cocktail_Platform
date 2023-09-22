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
	String address1;
	String address2;
	int postCode;
	String brn;
	String phone;
	String runningTime;
	String dayOff;
	String info;
	String originFilename;
	String savedFilename;
	float storeReviewScore;
}
