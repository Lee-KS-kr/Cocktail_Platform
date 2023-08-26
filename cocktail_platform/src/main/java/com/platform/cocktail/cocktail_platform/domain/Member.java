package com.platform.cocktail.cocktail_platform.domain;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class Member implements UserDetails{
	String memberId;
	String memberPw;
	String memberName;
	MemberType memberType;
	String phone;
	String email;
	boolean isEnable;
}
