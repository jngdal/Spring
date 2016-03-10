package com.example.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.example.domain.Role;
import com.example.domain.SocialUsers;
import com.example.testlogin.SocialMediaService;
import com.example.until.SignInUtils;

@Controller
public class SignUpController {
	@Autowired
	private org.springframework.social.connect.ConnectionFactoryLocator ConnectionFactoryLocator;
	@Autowired
	org.springframework.social.connect.UsersConnectionRepository UsersConnectionRepository;
	@Autowired
	private SignInUtils signInUtils;
	@Autowired
	private com.example.repositories.SocialUserRepository SocialUserRepository;

	 @PostConstruct
	   private void init() {
	    // hack for the login of facebook.
	    try {
	        String[] fieldsToMap = {
	                "id", "about", "age_range", "address", "bio", "birthday", "context", "cover", "currency", "devices", "education", "email",
	                "favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", "install_type",
	                "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name", "name", "name_format",
	                "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", "significant_other",
	                "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "viewer_can_send_gift",
	                "website", "work"
	        };

	        Field field = Class.forName("org.springframework.social.facebook.api.UserOperations").
	                getDeclaredField("PROFILE_FIELDS");
	        field.setAccessible(true);

	        Field modifiers = field.getClass().getDeclaredField("modifiers");
	        modifiers.setAccessible(true);
	        modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	        field.set(null, fieldsToMap);

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String redirectRequestToRegistrationPage(WebRequest request,
			HttpSession session) {
		Connection<?> connection = new ProviderSignInUtils(
				ConnectionFactoryLocator, UsersConnectionRepository)
				.getConnectionFromSession(request);
		System.out.println(connection);
		if (connection != null) {
			try {
				UserProfile socialMediaProfile = connection.fetchUserProfile();
				SocialUsers user = new SocialUsers();
				user.setEmail(socialMediaProfile.getEmail());
				user.setFirstName(socialMediaProfile.getFirstName());
				user.setLastName(socialMediaProfile.getLastName());
				ConnectionKey providerKey = connection.getKey();
				user.setSignInProvider(SocialMediaService.valueOf(providerKey
						.getProviderId().toUpperCase()));
				user.setRole(Role.ROLE_USER);
				user.setPassword(user.getEmail());
				System.out.println(user.toString());
				if (SocialUserRepository.findByEmail(user.getEmail()) == null)
					SocialUserRepository.save(user);
				signInUtils.signin(user.getEmail());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}		
			
			

		}

		if (session != null) {
			SavedRequest savedRequest = (SavedRequest) session
					.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if (savedRequest != null) {
				System.out.println(savedRequest.getRedirectUrl());
				return "redirect:"+savedRequest.getRedirectUrl();
			}else	{
				return "redirect:/";
			}
		} else {
			return "redirect:/";
		}
	}
}
