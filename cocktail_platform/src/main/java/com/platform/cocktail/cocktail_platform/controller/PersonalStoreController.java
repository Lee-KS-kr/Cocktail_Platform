package com.platform.cocktail.cocktail_platform.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.Reservation;
import com.platform.cocktail.cocktail_platform.domain.Schedule;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("personal/store")
public class PersonalStoreController {
	@GetMapping("list")
	public String list(Model m) {
		ArrayList<StoreInfo> list = new ArrayList<>();
		m.addAttribute("list", list);
		return "";
	}
	
	@GetMapping("storeDetail")
	public String storeDetail(int storeCode, Model m) {
		StoreInfo info = new StoreInfo();
		m.addAttribute("storeInfo", info);
		return "";
	}
	
	@GetMapping("menuList")
	public ArrayList<Menu> menuList(int storeCode){
		ArrayList<Menu> list = new ArrayList<>();
		return list;
	}
	
	@GetMapping("reserve")
	public String reserve(int storeCode, String date, String time, Model m) {
		Schedule schedule = new Schedule();
		boolean canReserve = true;
		m.addAttribute("schedule", schedule);
		m.addAttribute("canReserve", canReserve);
		return "";
	}
	
	@PostMapping("reserve")
	public String reserve(Reservation reserve, String phone) {
		reserve.setMemo(reserve.getMemo() + phone);
		return "";
	}
}
