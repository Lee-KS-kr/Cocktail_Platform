package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.cocktail.cocktail_platform.config.CodeConfig;
import com.platform.cocktail.cocktail_platform.dao.StoreDAO;
import com.platform.cocktail.cocktail_platform.domain.Category;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.MenuPreference;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.ReservationState;
import com.platform.cocktail.cocktail_platform.domain.Schedule;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class StoreServiceImple implements StoreService {
	@Autowired
	private StoreDAO dao;
	@Autowired
	CodeConfig cc;

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
		for (Reservation rsv : list) 
			rsv.setStoreName(dao.getStoreinfoByCode(storeCode).getStoreName());
		
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
		for (StoreInfo s : list) 
			s.setStoreReviewScore(dao.getReviewScoreByCode(s.getStoreCode()));
		
		return list;
	}

	@Override
	public StoreInfo getStoreinfoByCode(int storeCode) {
		StoreInfo info = dao.getStoreinfoByCode(storeCode);
		return info;
	}

	@Override
	public StoreReview findReviewByCodeAndId(String memberid, int orderCode) {
		HashMap<String, Object> idCodeMap = new HashMap<>();
		idCodeMap.put("memberid", memberid);
		idCodeMap.put("orderCode", orderCode);
		StoreReview reivew = dao.findReviewByCodeAndId(idCodeMap);
		
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
		HashMap<String, Integer> map = cc.codeMap();
		
		int n = dao.insertStoreReview(review);
		log.debug("{}", review);
		
		for (int i = 0; i < menuNum.length; i++) {
			MenuPreference mp 
				= MenuPreference.builder()
								.menuNum(menuNum[i])
								.memberId(review.getMemberId())
								.weather(map.get(weather[i]))
								.ageGroup(map.get(ageGroup[i]))
								.companion(map.get(companion[i]))
								.event(map.get(event[i]))
								.build();
			
			dao.insertMenuPreference(mp);
			log.debug("mp {}", mp);
		}
		
		return n;
	}

	@Override
	public HashMap<String, Boolean> getCapacityByDateTime(int storeCode, String date) {
		HashMap<String, Boolean> resultMap = new HashMap<>();
		Schedule s = getScheduleByCode(storeCode);
		String[] timeArr = s.getTimes().split(",");
		int capacity = dao.getCapacityByCode(storeCode);
		
		for (String str : timeArr) {
			String datetime = date + str;
			HashMap<String, Object> map = new HashMap<>();
			map.put("storeCode", storeCode);
			map.put("date", datetime);
			boolean canReserve = capacity > dao.getReservedCountByDatetime(map);
			resultMap.put(datetime, canReserve);
		}
		
		return resultMap;
	}

	@Override
	public Schedule getScheduleByCode(int storeCode) {
		Schedule s = dao.getScheduleByCode(storeCode);
		return s;
	}

	@Override
	public ArrayList<Menu> getMenulistByNum(String[] menus) {
		ArrayList<Menu> list = new ArrayList<>();
		for(String str : menus) {
			String[] menu = str.split("_");
			list.add(getMenuInfoByNum(Integer.parseInt(menu[0])));
		}
		
		return list;
	}

	@Override
	public ArrayList<Reservation> getReservelistById(String memberId) {
		ArrayList<Reservation> list = dao.getReservelistById(memberId);
		for (Reservation rsv : list) 
			rsv.setStoreName(dao.getStoreinfoByCode(rsv.getStoreCode()).getStoreName());
		
		return list;
	}

	@Override
	public int insertStoreinfo(StoreInfo info) {
		int n = dao.insertStoreinfo(info);
		return n;
	}

	@Override
	public Reservation getReservationByCode(String reserveCode) {
		Reservation reservation = dao.getReservationByCode(reserveCode);
		reservation.setStoreName(dao.getStoreinfoByCode(reservation.getStoreCode()).getStoreName());
		return reservation;
	}

	@Override
	public String hasFileFromStore(int storeCode) {
		String str = dao.hasFileFromStore(storeCode);
		return str;
	}

	@Override
	public int deleteFileFromStore(StoreInfo store) {
		int n = dao.deleteFileFromStore(store);
		return n;
	}

	@Override
	public ArrayList<StoreInfo> getStoreByCocktailName(String cocktailName) {
		ArrayList<StoreInfo> list = dao.getStoreByCocktailName(cocktailName);
		log.debug("{}", list);
		for (StoreInfo s : list) {
			log.debug("before {}", s);
			s.setStoreReviewScore(dao.getReviewScoreByCode(s.getStoreCode()));
			log.debug("after s {}", s);
		}
		
		return list;
	}

	@Override
	public int insertMenu(Menu menu) {
		log.debug("메뉴 저장 시도중");
		int n = dao.insertMenu(menu);
		log.debug("메뉴 저장 완료 {}", menu);
		return n;
	}

	@Override
	public ArrayList<StoreInfo> searchStore(String searchWord) {
		ArrayList<StoreInfo> list = dao.searchStore(searchWord);
		for (StoreInfo s : list) 
			s.setStoreReviewScore(dao.getReviewScoreByCode(s.getStoreCode()));
		
		log.debug("{}", list);
		return list;
	}

	@Override
	public ArrayList<Menu> getMenuByCategory(int storeCode, Category category) {
		Menu menu = new Menu();
		menu.setStoreCode(storeCode);
		menu.setCategory(category);
		
		ArrayList<Menu> list = dao.getMenuByCategory(menu);
		return list;
	}

	@Override
	public boolean getReviewWrote(String orderCode) {
		return dao.getScoreReviewByCode(orderCode) != null;
	}
	
}
