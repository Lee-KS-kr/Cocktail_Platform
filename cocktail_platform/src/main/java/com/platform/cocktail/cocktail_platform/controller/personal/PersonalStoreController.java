package com.platform.cocktail.cocktail_platform.controller.personal;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.ReservationState;
import com.platform.cocktail.cocktail_platform.domain.Schedule;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("personal/store")
public class PersonalStoreController {
	@Autowired
	private StoreService sService;
	
	@GetMapping("list")
	public String list(Model m) {
		ArrayList<StoreInfo> list = sService.getAllStorelist();
		m.addAttribute("list", list);
		return "personalView/storeList";
	}
	
	@GetMapping("storeDetail")
	public String storeDetail(int storeCode, Model m) {
		StoreInfo info = sService.getStoreinfoByCode(storeCode);
		m.addAttribute("storeInfo", info);
		return "personalView/storeDetail";
	}
	
	@GetMapping("reserve")
	public String reserve(int storeCode, String date, Model m) {
		Schedule schedule = sService.getScheduleByCode(storeCode);
		HashMap<String, Boolean> capacity = checkCapacity(storeCode, date);
		
		m.addAttribute("schedule", schedule);
		m.addAttribute("capacity", capacity);
		return "";
	}
	
	@ResponseBody
	@GetMapping("checkCapacity")
	public HashMap<String, Boolean> checkCapacity(int storeCode, String date) {
		return sService.getCapacityByDateTime(storeCode, date);
	}
	
	@PostMapping("reserve")
	public String reserve(Reservation reserve) {
		reserve.setReserveState(ReservationState.apply);
		sService.insertReservation(reserve);
		return "redirect:/personal/store/storeDetail?storeCode=" + reserve.getStoreCode();
	}
}
