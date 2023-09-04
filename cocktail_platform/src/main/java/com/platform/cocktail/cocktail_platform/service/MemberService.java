package com.platform.cocktail.cocktail_platform.service;

import java.util.ArrayList;

import com.platform.cocktail.cocktail_platform.domain.Member;
import com.platform.cocktail.cocktail_platform.domain.MemberType;
import com.platform.cocktail.cocktail_platform.domain.Order;
import com.platform.cocktail.cocktail_platform.domain.StoreReview;
import com.platform.cocktail.cocktail_platform.domain.Taste;

public interface MemberService {

	int insertMember(Member m);

	int insertTaste(Taste t);

	Member findMemberById(String memberId);

	Member findMemberByEmail(String email);

	Taste findTasteById(String username);

	int resetPw(Member m);

	int updateMember(Member mem);

	int updateTaste(Taste t);

	int unableMember(String memberId);

}
