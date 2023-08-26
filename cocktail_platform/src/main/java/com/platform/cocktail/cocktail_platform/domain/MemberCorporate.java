package com.platform.cocktail.cocktail_platform.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class MemberCorporate extends Member {
	@Override
	public String toString() {
		return "MemberCorporate [memberId=" + memberId + ", memberPw=" + memberPw + ", memberType=" + memberType
				+ ", phone=" + phone + ", email=" + email + ", memberName=" + memberName + ", isEnable=" + isEnable + "]";
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.memberPw;
	}
	
	@Override
	public String getUsername() {
		return this.memberId;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isEnable;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isEnable;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isEnable;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnable;
	}

}
