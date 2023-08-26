package com.platform.cocktail.cocktail_platform.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberCorporate;
import com.platform.cocktail.cocktail_platform.domain.MemberPerson;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

@Mapper
public interface MemberDAO {

	int insertMemberPerson(MemberPerson m);

	int insertMemberCorporate(MemberCorporate m);

	MemberPerson findMemberPersonById(String memberId);

	MemberCorporate findMemberCorporateById(String memberId);
	
	MemberPerson findMemberPersonByEmail(String email);
	
	MemberCorporate findMemberCorporateByEmail(String email);

	int resetPersonPw(MemberPerson m);

	int resetCorporatePw(MemberCorporate m);

	int updateMemberPerson(MemberPerson m);

	int updateMemberCorporate(MemberCorporate m);

	int updateTaste();

	int unableMember(String memberId);



}
