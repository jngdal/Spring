package com.example.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.PersistentLogin;
import com.example.domain.User;

@Repository
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository  {

	@Autowired
	PersistentLoginReposity PersistentLoginReposity;
	@Autowired
	UserRepository  UserRepository;
	@Override

	public void createNewToken(PersistentRememberMeToken token) {
		System.out.println(token.getTokenValue());
		
		PersistentLogin persistentLogin= new PersistentLogin();
		User user=UserRepository.findByusername(token.getUsername());
		persistentLogin.setUser(user);
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		PersistentLoginReposity.save(persistentLogin);
		System.out.println("createNewToken");
	}

	@Override
	
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		// TODO Auto-generated method stub
		try {
			PersistentLogin persistentLogin=PersistentLoginReposity.getOne(series);
			persistentLogin.setLast_used(lastUsed);
			persistentLogin.setToken(tokenValue);
			PersistentLoginReposity.save(persistentLogin);
			System.out.println("updateToken");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override

	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		// TODO Auto-generated method stub
		try {
			PersistentLogin persistentLogin=PersistentLoginReposity.getOne(seriesId);
			PersistentRememberMeToken t = new PersistentRememberMeToken(persistentLogin.getUser().getUsername(), seriesId, persistentLogin.getToken(), persistentLogin.getLast_used());
			System.out.println("getTokenForSeries");
			return t;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}

	@Override	
	public void removeUserTokens(String username) {
		try {
			System.out.println(username);	
			User user=UserRepository.findByusername(username);
			List<PersistentLogin> persistentLogin=PersistentLoginReposity.findByuser(user);
			PersistentLoginReposity.deleteInBatch(persistentLogin);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
