package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberPerson;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;

public interface MemberService {

	int insertMember(Member m);

	Member findMemberById(String memberId, MemberType type);

	Member findMemberByEmail(String email, MemberType type);

	int resetPw(Member m);

	int updateMember(Member mem);

	int updateTaste();

	int unableMember(String memberId);



}
