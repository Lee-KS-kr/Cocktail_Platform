package com.platform.cocktail.cocktail_platform.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

@Mapper
public interface MemberDAO {

	int insertMember(Member m);

	Member findMemberById(String memberId);
	
	Member findMemberByEmail(String email);

	int resetMemberPw(Member m);

	int updateMember(Member m);

	int updateTaste();

	int unableMember(String memberId);
}
