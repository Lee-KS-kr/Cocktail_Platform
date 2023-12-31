package com.platform.cocktail.cocktail_platform.controller.corporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.platform.cocktail.cocktail_platform.api.mail_send.service.EmailService;
import com.platform.cocktail.cocktail_platform.config.FileService;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("corporate/member")
public class CorporateAjaxController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private StoreService sService;
	
	@Value("${file.path.store.locatioin}")
	String storePath;

	@Value("${file.name.store.default}")
	String defaultStoreImg;

	@GetMapping("getPrivacy")
	public Member getPrivacy(@AuthenticationPrincipal UserDetails user) {
		Member m = service.findMemberById(user.getUsername());
		log.debug("found member : {}", m);
		m.setMemberId(user.getUsername());
		log.debug("member : {}", m);
		return m;
	}
	
	@PostMapping("editPrivacyInfo")
	public void editPrivacyInfo(Member m, @AuthenticationPrincipal UserDetails user) {
		m.setMemberId(user.getUsername());
		service.updateMember(m);
		log.debug("edit : {}", m);
	}
	
	@GetMapping("getStoreinfo")
	public StoreInfo editStorepage(@AuthenticationPrincipal UserDetails user) {
		StoreInfo store = sService.getStoreById(user.getUsername());
		log.debug("found store : {}", store);
		return store;
	}
	
	@PostMapping("editStorepage")
	public void editStorepage(@AuthenticationPrincipal UserDetails user, StoreInfo store, MultipartFile upload) {
		store.setMemberId(user.getUsername());
		log.debug("now store : {}", store);
		
		if(upload != null && !upload.isEmpty()) {
			log.debug("upload : {}", upload.getName());
			store.setSavedFilename(sService.hasFileFromStore(store.getStoreCode()));
			if(store.getSavedFilename() != null) {
				String fullpath = storePath + "/" + store.getSavedFilename();
				FileService.deleteFile(fullpath);
				
				store.setOriginFilename(null);
				store.setSavedFilename(null);
				sService.deleteFileFromStore(store);
			}

			String savedfile = FileService.saveFile(upload, storePath);
			store.setOriginFilename(upload.getOriginalFilename());
			store.setSavedFilename(savedfile);
		} else if(upload == null || upload.isEmpty()) {
			store.setOriginFilename(defaultStoreImg);
			store.setSavedFilename(defaultStoreImg);
		}

		log.debug("new store : {}", store);
		sService.updateStoreinfo(store);
	}
	
}
