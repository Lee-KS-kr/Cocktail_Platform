package com.platform.cocktail.cocktail_platform.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.MemberDAO;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;
import com.platform.cocktail.cocktail_platform.domain.Taste;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImple implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private HashMap<String, Integer> map;
	
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
		String taste = map.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), t.getCocktailTaste()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0);
		String flavor = map.entrySet().stream()
				.filter(entry -> Objects.equals(entry.getValue(), t.getCocktailFlavor()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList())
				.get(0);
		
		t.setTasteArr(taste.split(","));
		t.setFlavorArr(flavor.split(","));
		
		return t;
	}
	
	public Taste changeStringToInt(Taste t) {
		String taste = StringUtils.join(t.getTasteArr());
		String flavor = StringUtils.join(t.getFlavorArr());
		
		t.setCocktailTaste(map.get(taste));
		t.setCocktailFlavor(map.get(flavor));
		
		return t;
	}
}
