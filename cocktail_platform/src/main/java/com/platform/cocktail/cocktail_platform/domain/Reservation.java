package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	String reserveCode;
	int storeCode;
	String memberId;
	String reserveDate;
	String reservationName;
	String reservationPhone;
	String request;
	int reserveHead;
	ReservationState reserveState;
	String storeName;
}
