package com.platform.cocktail.cocktail_platform.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberPerson extends Member {
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.memberPw;
	}

	@Override
	public String getUsername() {
		return this.memberId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isEnable;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isEnable;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isEnable;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnable;
	}
}
