package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreReview {
	int storeReviewNum;
	int storeCode;
	String memberid;
	int storeReviewScore;
	String storeReviewText;
	String reviewDate;
	String originFilename;
	String savedFilename;
}
