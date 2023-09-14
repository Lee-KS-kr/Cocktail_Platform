package com.platform.cocktail.cocktail_platform.controller.corporate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.platform.cocktail.cocktail_platform.config.FileService;
import com.platform.cocktail.cocktail_platform.domain.Menu;
import com.platform.cocktail.cocktail_platform.domain.OrderState;
import com.platform.cocktail.cocktail_platform.domain.OrderTemp;
import com.platform.cocktail.cocktail_platform.service.OrderService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("corporate/store")
public class CorporateAjaxStoreController {
	@Autowired
	private OrderService oService;
	@Autowired
	private StoreService sService;
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	@PostMapping("orderList")
	public ArrayList<OrderTemp> orderList(int storeCode){
		ArrayList<OrderTemp> orderList = oService.getTemporderlistByCode(storeCode);
		return orderList;
	}
	
	@GetMapping("orderAccept")
	public void orderAccept(int tempOrderkey) {
		sService.changeOrderState(tempOrderkey, OrderState.received);
	}
	
	@GetMapping("orderRefuse")
	public void orderRefuse(int tempOrderkey) {
		sService.changeOrderState(tempOrderkey, OrderState.refuesed);
	}
	
	@PostMapping("addMenu")
	public void addMenu(Menu menu, MultipartFile upload) {
		uploadPath += "/menu";
		
		if(upload != null && !upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, uploadPath);
			menu.setOriginFilename(upload.getOriginalFilename());
			menu.setSavedFilename(savedfile);
		}
		log.debug("저장할 글 : {}", menu);
	}
}
