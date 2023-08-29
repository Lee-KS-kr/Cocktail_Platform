package com.platform.cocktail.cocktail_platform.api.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.platform.cocktail.cocktail_platform.dao.MemberDAO;
import com.platform.cocktail.cocktail_platform.domain.MemberPerson;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	@Autowired
	private MemberDAO dao;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		OAuth2User oAuth2User = super.loadUser(userRequest);
		log.info("getAttributes : {}", oAuth2User.getAttributes());
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		String providerId = oAuth2User.getAttribute("sub");
		String email = oAuth2User.getAttribute("email");
		String loginId = provider + "_" + providerId;
		String loginPw = encoder.encode(loginId);
		
		MemberPerson user = dao.findMemberPersonById(loginId);
		if(user == null) {
			user = MemberPerson.builder()
								.memberId(loginId)
								.memberPw(loginPw)
								.memberName(oAuth2User.getAttribute("name"))
								.gender("N")
								.isChannel(true)
								.channel(provider)
								.email(email)
								.build();
			
			dao.insertMemberPerson(user);
		}
		
		return new PrincipalDetails(user, oAuth2User.getAttributes());
	}
}
