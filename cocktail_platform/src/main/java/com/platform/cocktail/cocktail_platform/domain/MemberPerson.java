package com.platform.cocktail.cocktail_platform.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPerson extends Member {
	String memberId;
	String memberPw;
	String memberName;
	MemberType memberType;
	String phone;
	String email;
	boolean isEnable;
	String gender;
	String birthday;
	int point;
	boolean isChannel;
	String channel;
	String createdDate;
	
	@Override
	public String toString() {
		return "MemberPerson [" + "memberId=" + memberId + ", memberPw=" + memberPw + ", memberType=" + memberType 
				+ ", phone=" + phone + ", email=" + email + ", memberName=" + memberName + ", isEnable=" + isEnable 
				+  "gender=" + gender + ", birthday=" + birthday + ", point=" + point + ", isChannel="
				+ isChannel + ", channel=" + channel + ", createdDate=" + createdDate + "]";
	}
}
