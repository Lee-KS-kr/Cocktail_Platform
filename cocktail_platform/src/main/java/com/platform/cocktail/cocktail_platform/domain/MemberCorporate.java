package com.platform.cocktail.cocktail_platform.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCorporate extends Member implements UserDetails {
	String memberId;
	String memberPw;
	String memberName;
	MemberType memberType;
	String phone;
	String email;
	boolean isEnable;
	
	@Override
	public String toString() {
		return "MemberCorporate [memberId=" + memberId + ", memberPw=" + memberPw + ", memberType=" + memberType
				+ ", phone=" + phone + ", email=" + email + ", memberName=" + memberName + ", isEnable=" + isEnable + "]";
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
