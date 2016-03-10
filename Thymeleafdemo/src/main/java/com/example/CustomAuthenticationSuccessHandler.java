package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.example.aop.NotifyAll;
import com.example.domain.User;

@Component
public class CustomAuthenticationSuccessHandler implements
		AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@Autowired
	com.example.repositories.UserRepository UserRepository;

	@NotifyAll
	@Override	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (response.isCommitted()) {
			return;
		}
		String targetUrl = null;

		User user = UserRepository.findByusername(authentication.getName());
		if (user.getDetailuser() == null) {
			System.out.println("detail user is null");
			targetUrl = "/user";
		} else {
			user.getDetailuser().setStatus(true);
			UserRepository.save(user);
			
			SavedRequest savedRequest = (SavedRequest) request.getSession()
					.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
			if (savedRequest != null) {
				System.out.println(savedRequest.getRedirectUrl());
				targetUrl = savedRequest.getRedirectUrl();
			} else {
				targetUrl = "/websocket/chat";
			}
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);

	}

}
