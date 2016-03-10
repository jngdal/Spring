package com.example.angular.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.aop.NotifyAll;
import com.example.domain.DetailUser;
import com.example.domain.User;
import com.example.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
@PreAuthorize(value="hasRole('ROLE_USER')")
@Controller
@RequestMapping(value="/user")

public class ManagerUserController {
	
	@Autowired UserRepository UserRepository;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "user/index";
	}
	@NotifyAll
	@RequestMapping(method=RequestMethod.POST)
	@JsonIgnoreProperties("user")
	public @ResponseBody void update(@RequestBody DetailUser detailUser){
		
		
		Authentication authendication=SecurityContextHolder.getContext().getAuthentication();
		User user=UserRepository.findByusername(authendication.getName());
		detailUser.setStatus(true);
		user.setDetailuser(detailUser);
		UserRepository.save(user);
		
		
	}
	
	@RequestMapping(value="/language",method=RequestMethod.GET)
	public @ResponseBody Map<String,String> getMessageLanguage(@RequestParam String lang) throws IOException{
		Resource resource=null;
		switch (lang) {
		case "vi":
			resource = new ClassPathResource("static/angularjs/user/i18/vi.json");
			break;

		default:
			resource = new ClassPathResource("static/angularjs/user/i18/en.json");
			break;

		
		}
		ObjectMapper mapper = new ObjectMapper();
		InputStream resourceInputStream = resource.getInputStream();
		@SuppressWarnings("unchecked")
		Map<String,String> map = mapper.readValue(resourceInputStream, Map.class);
		Map<String, String>errormap=getErrorMessageLanguage(lang);
		map.putAll(errormap);		
		return map;
		
		
	}
	
	
	private Map<String, String> getErrorMessageLanguage(String lang) throws IOException{
		Resource resource=null;
		switch (lang) {
		case "vi":
			resource = new ClassPathResource("static/angularjs/user/i18/error/vi.json");
			break;

		default:
			resource = new ClassPathResource("static/angularjs/user/i18/error/en.json");
			break;

		
		}
		ObjectMapper mapper = new ObjectMapper();
		InputStream resourceInputStream = resource.getInputStream();
		@SuppressWarnings("unchecked")
		Map<String,String> map = mapper.readValue(resourceInputStream, Map.class);
				
		return map;
		
		
	}
	

}
