package com.platform.cocktail.cocktail_platform.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.MemberDAO;
import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberCorporate;
import com.platform.cocktail.cocktail_platform.domain.MemberPerson;
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
		int n = 0;
		m.setMemberPw(encoder.encode(m.getMemberPw()));
		
		if(m instanceof MemberPerson) 
			n = dao.insertMemberPerson((MemberPerson) m);
		else
			n = dao.insertMemberCorporate((MemberCorporate) m);
			
		return n;
	}

	@Override
	public Member findMemberById(String memberId, MemberType type) {
		Member m;
		if(type.equals(MemberType.privateMem))
			m = dao.findMemberPersonById(memberId);
		else
			m = dao.findMemberCorporateById(memberId);
			
		return m;
	}
	
	@Override
	public Member findMemberByEmail(String email, MemberType type) {
		Member m;
		if(type.equals(MemberType.privateMem))
			m = dao.findMemberPersonByEmail(email);
		else
			m = dao.findMemberCorporateByEmail(email);
			
		return m;
	}

	@Override
	public int resetPw(Member m) {
		int n = 0;
		m.setMemberPw(encoder.encode(m.getMemberPw()));
		
		if(m instanceof MemberPerson) 
			n = dao.resetPersonPw((MemberPerson) m);
		else
			n = dao.resetCorporatePw((MemberCorporate) m);
		
		return n;
	}

	@Override
	public int updateMember(Member m) {
		int n = 0;
		if(m instanceof MemberPerson) 
			n = dao.updateMemberPerson((MemberPerson) m);
		else
			n = dao.updateMemberCorporate((MemberCorporate) m);
		
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
