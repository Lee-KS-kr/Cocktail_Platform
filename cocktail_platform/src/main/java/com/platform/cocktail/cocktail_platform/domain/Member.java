package com.platform.cocktail.cocktail_platform.domain;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class Member implements UserDetails{
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
