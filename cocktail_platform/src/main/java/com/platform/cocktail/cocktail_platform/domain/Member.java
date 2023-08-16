package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	String memberId;
	String memberPw;
	MemberType memberType;
	String phone;
	String email;
	String name;
	boolean isEnable;
	
	enum MemberType{
		admin, personal, corporate
	}
}
