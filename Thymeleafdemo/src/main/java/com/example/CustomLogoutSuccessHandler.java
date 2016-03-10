package com.example;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.aop.NotifyAllAop;
import com.example.domain.User;
import com.example.repositories.UserRepository;
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	@Autowired
	UserRepository UserRepository;
	@Autowired 
	SimpMessagingTemplate SimpMessagingTemplate;
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		User user= UserRepository.findByusername(authentication.getName());
		user.getDetailuser().setStatus(false);		
		UserRepository.save(user);		
		SimpMessagingTemplate.convertAndSend(NotifyAllAop.WEBSOCKET_TOPIC, new Date());
		System.out.println("/login?logout");
		response.sendRedirect("/login?logout");
		
	}

}
