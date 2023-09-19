package com.platform.cocktail.cocktail_platform.controller.corporate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.platform.cocktail.cocktail_platform.config.FileService;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.StoreInfo;
import com.platform.cocktail.cocktail_platform.service.MemberService;
import com.platform.cocktail.cocktail_platform.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("corporate/member")
public class CorporateMemberController {
	@Autowired
	private MemberService mService;
	@Autowired
	private StoreService sService;
	@Value("${file.path.store.locatioin}")
	String storePath;
	@Value("${file.name.store.default}")
	String defaultStoreImg;
	
	@GetMapping("mypage")
	public String mypage(@AuthenticationPrincipal UserDetails user) {
		return "corporateView/corpMyPage";
	}
	
	@GetMapping("resetPw")
	public String resetPw() {
		return "corporateView/resetPwCorp";
	}
	
	@PostMapping("resetPw")
	public String resetPw(@AuthenticationPrincipal UserDetails user, String memberPw) {
		Member m = new Member();
		m.setMemberId(user.getUsername());
		m.setMemberPw(memberPw);
		mService.resetPw(m);
		return "redirect:/corporate/home";
	}
	
	@GetMapping("insertStoreinfo")
	public String insertStoreinfo(String memberId, Model m) {
		m.addAttribute("memberId", memberId);
		return "corporateView/storeInfoForm";
	}
	
	@PostMapping("insertStoreinfo")
	public String insertStoreinfo(StoreInfo info, MultipartFile upload) {
		if(upload != null && !upload.isEmpty()) {
			String savedfile = FileService.saveFile(upload, storePath);
			info.setOriginFilename(upload.getOriginalFilename());
			info.setSavedFilename(savedfile);
		} else {
			info.setOriginFilename(defaultStoreImg);
			info.setSavedFilename(defaultStoreImg);
		}
		
		log.debug("저장할 글 : {}", info);
		sService.insertStoreinfo(info);
		return "redirect:/";
	}
}
