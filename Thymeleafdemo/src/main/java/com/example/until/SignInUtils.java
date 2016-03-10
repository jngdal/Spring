package com.example.until;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.repositories.SocialUserRepository;
@Service
public class SignInUtils {
	@Autowired
    SocialUserRepository  socialUserRepository;
	 private Set<GrantedAuthority> authorities;
	public  void signin(String userId) {
		authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(socialUserRepository.findByEmail(userId).getRole().toString()));
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null,authorities));	
	}
}
