package com.platform.cocktail.cocktail_platform.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.MenuPreference;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.Schedule;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

@Mapper
public interface StoreDAO {

	StoreInfo getStoreById(String memberId);

	int updateStoreinfo(StoreInfo store);

	ArrayList<Reservation> getReservelistByCode(int storeCode);

	Reservation findReservationByCode(String reserveCode);

	int changeReservationState(Reservation r);

	int insertReservation(Reservation reserve);

	OrderTemp findOrderByKey(int tempOrderkey);

	int deleteOrder(int tempOrderkey);

	ArrayList<Menu> getMenulistByCode(int storeCode);

	Menu getMenuInfoByNum(int menuNum);

	ArrayList<StoreInfo> getAllStorelist();

	StoreInfo getStoreinfoByCode(int storeCode);

	StoreReview findReviewByCodeAndId(HashMap<String, Object> map);

	ArrayList<StoreReview> getReviewlistByCode(int storeCode);

	int insertStoreReview(StoreReview review);

	int insertMenuPreference(ArrayList<MenuPreference> list);

	int getCapacityByCode(int storeCode);

	int getReservedCountByDatetime(HashMap<String, Object> map);

	Schedule getScheduleByCode(int storeCode);

	ArrayList<Reservation> getReservelistById(String memberId);

	int insertStoreinfo(StoreInfo info);

	Reservation getReservationByCode(String reserveCode);

	String hasFileFromStore(int storeCode);

	int deleteFileFromStore(StoreInfo store);

	float getReviewScoreByCode(int storeCode);
}
