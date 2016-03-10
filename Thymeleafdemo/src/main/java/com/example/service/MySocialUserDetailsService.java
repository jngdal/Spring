package com.example.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.example.domain.Role;
import com.example.repositories.SocialUserRepository;

@Service("socialUsersDetailsService")
public class MySocialUserDetailsService implements SocialUserDetailsService {

	@Autowired
	SocialUserRepository socialUserRepository;

	@Override
	public SocialUserDetails loadUserByUserId(String email)
			throws UsernameNotFoundException, DataAccessException {
		com.example.domain.SocialUsers currentuser = socialUserRepository
				.findByEmail(email);
		List<GrantedAuthority> authorities = buildUserAuthority(currentuser
				.getRole());

		return buildUserForAuthentication(currentuser, authorities);
	}

	private SocialUser buildUserForAuthentication(
			com.example.domain.SocialUsers user,
			List<GrantedAuthority> authorities) {
		return new SocialUser(user.getEmail(), user.getPassword(), true, true,
				true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Role role) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		setAuths.add(new SimpleGrantedAuthority(role.toString()));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

}
