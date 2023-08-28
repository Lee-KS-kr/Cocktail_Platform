package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.StoreDAO;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.MenuPreference;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.ReservationState;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StoreServiceImple implements StoreService {
	@Autowired
	private StoreDAO dao;

	@Override
	public StoreInfo getStoreById(String memberId) {
		StoreInfo s = dao.getStoreById(memberId);
		return s;
	}

	@Override
	public int updateStoreinfo(StoreInfo store) {
		int n = dao.updateStoreinfo(store);
		return n;
	}

	@Override
	public ArrayList<Reservation> getReservelistByCode(int storeCode) {
		ArrayList<Reservation> list = dao.getReservelistByCode(storeCode);
		return list;
	}

	@Override
	public int changeReservationState(int storeCode, String reserveCode, ReservationState state) {
		Reservation r = dao.findReservationByCode(reserveCode);
		r.setReserveState(state);
		
		int n = dao.changeReservationState(r);
		return n;
	}

	@Override
	public int insertReservation(Reservation reserve) {
		int n = dao.insertReservation(reserve);
		return n;
	}

	@Override
	public int changeOrderState(int tempOrderkey, OrderState state) {
		int n = 0;
		OrderTemp t = dao.findOrderByKey(tempOrderkey);
		
		if(state == OrderState.refuesed)
			n = dao.deleteOrder(tempOrderkey);
		else {
			t.setState(OrderState.received);
		}
		
		return n;
	}

	@Override
	public ArrayList<Menu> getMenulistByCode(int storeCode) {
		ArrayList<Menu> list = dao.getMenulistByCode(storeCode);
		return list;
	}

	@Override
	public Menu getMenuInfoByNum(int menuNum) {
		Menu m = dao.getMenuInfoByNum(menuNum);
		return m;
	}

	@Override
	public ArrayList<StoreInfo> getAllStorelist() {
		ArrayList<StoreInfo> list = dao.getAllStorelist();
		return list;
	}

	@Override
	public StoreInfo getStoreinfoByCode(int storeCode) {
		StoreInfo info = dao.getStoreinfoByCode(storeCode);
		return info;
	}

	@Override
	public StoreReview findReviewByCodeAndId(String memberid, int orderCode) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("orderCode", orderCode);
		StoreReview reivew = dao.findReviewByCodeAndId(map);
		
		return reivew;
	}

	@Override
	public ArrayList<StoreReview> getReviewlistByCode(int storeCode) {
		ArrayList<StoreReview> list = dao.getReviewlistByCode(storeCode);
		return list;
	}

	@Override
	public int insertReview(StoreReview review, int[] menuNum, String[] weather, String[] ageGroup, String[] companion,
			String[] event) {
		ArrayList<MenuPreference> list = new ArrayList<>();
		
		for (int i = 0; i < menuNum.length; i++) {
			MenuPreference mp 
				= MenuPreference.builder()
								.menuNum(menuNum[i])
								.weather(weather[i])
								.ageGroup(ageGroup[i])
								.companion(companion[i])
								.event(event[i])
								.build();
			 
			list.add(mp);
		}
		
		dao.insertStoreReview(review);
		int n = dao.insertMenuPreference(list);
		
		return n;
	}
	
	
}