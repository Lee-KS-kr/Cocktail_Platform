package com.platform.cocktail.cocktail_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalMember extends Member {
	String gender;
	String birthday;
	int point;
	boolean isChannel;
	String channel;
	String createdDate;
	
	@Override
	public String toString() {
		return "PersonalMember [" + "memberId=" + memberId + ", memberPw=" + memberPw + ", memberType=" + memberType 
				+ ", phone=" + phone + ", email=" + email + ", name=" + name + ", isEnable=" + isEnable 
				+  "gender=" + gender + ", birthday=" + birthday + ", point=" + point + ", isChannel="
				+ isChannel + ", channel=" + channel + ", createdDate=" + createdDate + "]";
	}
}
