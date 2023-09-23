package com.platform.cocktail.cocktail_platform.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.config.CodeConfig;
import com.platform.cocktail.cocktail_platform.dao.MemberDAO;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.Taste;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImple implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	CodeConfig cc;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public int insertMember(Member m) {
		m.setMemberPw(encoder.encode(m.getMemberPw()));
		int n = dao.insertMember(m);
			
		return n;
	}
	
	@Override
	public int insertTaste(Taste t) {
		t = changeStringToInt(t);
		int n = dao.insertTaste(t);
		return n;
	}

	@Override
	public Member findMemberById(String memberId) {
		Member m = dao.findMemberById(memberId);
			
		return m;
	}
	
	@Override
	public Member findMemberByEmail(String email) {
		Member m = dao.findMemberByEmail(email);
			
		return m;
	}
	
	@Override
	public Taste findTasteById(String memberId) {
		Taste t = dao.findTasteById(memberId);
		t = changeIntToString(t);
		
		return t;
	}

	@Override
	public int resetPw(Member m) {
		m.setMemberPw(encoder.encode(m.getMemberPw()));
		int n = dao.resetMemberPw(m);
		
		return n;
	}

	@Override
	public int updateMember(Member m) {
		int n = dao.updateMember(m);

		return n;
	}

	@Override
	public int updateTaste(Taste t) {
		t = changeIntToString(t);
		int n = dao.updateTaste(t);
		return n;
	}

	@Override
	public int unableMember(String memberId) {
		int n = dao.unableMember(memberId);
		return n;
	}

	public Taste changeIntToString(Taste t) {
		HashMap<String, Integer> codeMap = cc.codeMap();
		String taste = codeMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), t.getCocktailTaste()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0);
		String flavor = codeMap.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), t.getCocktailFlavor()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0);
		
		t.setTaste(taste.split(","));
		t.setFlavor(flavor.split(","));
		
		return t;
	}
	
	public Taste changeStringToInt(Taste t) {
		String taste = StringUtils.join(t.getTaste());
		String flavor = StringUtils.join(t.getFlavor());
		log.debug("taste : {}, flavor : {}", taste, flavor);
		
		HashMap<String, Integer> codeMap = cc.codeMap();
		t.setCocktailTaste(codeMap.get(taste));
		t.setCocktailFlavor(codeMap.get(flavor));
		log.debug("cocktailtaste : {}, cocktailflavor : {}", t.getCocktailTaste(), t.getCocktailFlavor());
		
		return t;
	}

	@Override
	public int findMemberByPhone(String phone) {
		int n = dao.findMemberByPhone(phone);
		return n;
	}

	@Override
	public Member loginToOrder(String memberId, String memberPw) {
		HashMap<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("memberPw", memberPw);
		Member m = dao.loginToOrder(map);
		
		if(encoder.matches(memberPw, m.getMemberPw()))
			return m;
		else
			return null;
	}

	@Override
	public int changePw(String memberId, String memberPw, String newMemberPw) {
		Member m = loginToOrder(memberId, memberPw);
		if(m != null) {
			m.setMemberPw(encoder.encode(newMemberPw));
			int n = dao.resetMemberPw(m);
			return n;
		}
		
		return 0;
	}
}
