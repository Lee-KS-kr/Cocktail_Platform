package com.platform.cocktail.cocktail_platform.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;
import com.platform.cocktail.cocktail_platform.domain.Taste;

@Mapper
public interface MemberDAO {

	int insertMember(Member m);

	int insertTaste(Taste t);

	Member findMemberById(String memberId);
	
	Member findMemberByEmail(String email);

	Taste findTasteById(String memberId);

	int resetMemberPw(Member m);

	int updateMember(Member m);

	int updateTaste(Taste t);

	int unableMember(String memberId);


}
