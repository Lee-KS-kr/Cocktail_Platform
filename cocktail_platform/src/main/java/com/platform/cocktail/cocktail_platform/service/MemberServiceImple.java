package com.platform.cocktail.cocktail_platform.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.MemberDAO;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImple implements MemberService {
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public int insertMember(Member m) {
		m.setMemberPw(encoder.encode(m.getMemberPw()));
		int n = dao.insertMember(m);
			
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
	public int updateTaste() {
		int n = dao.updateTaste();
		return n;
	}

	@Override
	public int unableMember(String memberId) {
		int n = dao.unableMember(memberId);
		return n;
	}


}
