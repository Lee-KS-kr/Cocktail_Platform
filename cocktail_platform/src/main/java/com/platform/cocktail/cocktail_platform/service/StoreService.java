package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.platform.cocktail.cocktail_platform.domain.Category;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.ReservationState;
import com.platform.cocktail.cocktail_platform.domain.Schedule;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

public interface StoreService {

	StoreInfo getStoreById(String memberId);

	int updateStoreinfo(StoreInfo store);

	ArrayList<Reservation> getReservelistByCode(int storeCode);

	int changeReservationState(int storeCode, String reserveCode, ReservationState state);

	int insertReservation(Reservation reserve);

	int changeOrderState(int tempOrderkey, OrderState state);

	ArrayList<Menu> getMenulistByCode(int storeCode);

	Menu getMenuInfoByNum(int menuNum);

	ArrayList<StoreInfo> getAllStorelist();

	StoreInfo getStoreinfoByCode(int storeCode);

	StoreReview findReviewByCodeAndId(String username, int orderCode);

	ArrayList<StoreReview> getReviewlistByCode(int storeCode);

	int insertReview(StoreReview review, int[] menuNum, String[] weather, String[] ageGroup, String[] companion,
			String[] event);

	HashMap<String, Boolean> getCapacityByDateTime(int storeCode, String date);

	Schedule getScheduleByCode(int storeCode);

	ArrayList<Menu> getMenulistByNum(String[] menus);

	ArrayList<Reservation> getReservelistById(String username);

	int insertStoreinfo(StoreInfo info);

	Reservation getReservationByCode(String reserveCode);

	String hasFileFromStore(int storeCode);

	int deleteFileFromStore(StoreInfo store);

	ArrayList<StoreInfo> getStoreByCocktailName(String cocktailName);

	int insertMenu(Menu menu);

	ArrayList<StoreInfo> searchStore(String searchWord);

	ArrayList<Menu> getMenuByCategory(int storeCode, Category category);

	boolean getReviewWrote(String orderCode);

	int deleteMenuByNum(int menuNum);

}
