package com.platform.cocktail.cocktail_platform.api.oauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.platform.cocktail.cocktail_platform.domain.MemberPerson;

public class PrincipalDetails implements UserDetails, OAuth2User {
	
	private MemberPerson user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(MemberPerson user) {
		this.user = user;
	}
	
	public PrincipalDetails(MemberPerson user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collections = new ArrayList<>();
		collections.add(() -> {
			return user.getMemberType().name();
		});
		
		return collections;
	}

	@Override
	public String getPassword() {
		return user.getMemberPw();
	}

	@Override
	public String getUsername() {
		return user.getMemberId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.isEnable();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.isEnable();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.isEnable();
	}

	@Override
	public boolean isEnabled() {
		return user.isEnable();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return null;
	}

}
