package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member{
	String memberId;
	String memberPw;
	String memberName;
	String email;
	String gender;
	String birthday;
	String phone;
	int point;
	boolean isChannel;
	String channel;
	String createdDate;
	MemberType memberType;
	boolean isEnable;
}
