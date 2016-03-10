package com.example;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.filter.OncePerRequestFilter;

public class SingleEntryFilter extends OncePerRequestFilter {

	@Resource(name = "sessionRegistry")
	private SessionRegistry sessionRegistry;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	// The URI where the user should be redirected
	private String redirectURI;
	// The URI that needs to be watched for
	private List<String> guardURI;

	public String getRedirectURI() {
		return redirectURI;
	}

	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}

	public List<String> getGuardURI() {
		return guardURI;
	}

	public void setGuardURI(List<String> guardURI) {
		this.guardURI = guardURI;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			SessionInformation info = sessionRegistry
					.getSessionInformation(session.getId());
			System.out.println(session.getId());	
			if (info != null) {

				// Flag to indicate if user has already logged-in and session is
				// still valid
				Boolean hasLoggedIn = (Boolean) session
						.getAttribute("hasLoggedIn");

				if (hasLoggedIn != null) {

					
					// Loop list. We might have multiple URIs to guard for!

					for (String guard : guardURI) {
					
						if (request.getRequestURI().startsWith(guard) == true) {

							
							redirectStrategy.sendRedirect(request, response,
									redirectURI);

						}else{
							
							
						}
					}

				} else {
					// User is accessing site for the first time
					// Either he's old session has expired or he purposely
					// logged-out
					System.out.println("null");

					// This will only be removed once the session has expired
					// If it doesn't expire the user will not be able to login
					// again!
					session.setAttribute("hasLoggedIn", new Boolean(true));

					// Refreshes the internal lastRequest to the current date
					// and time.
					info.refreshLastRequest();
				}
			} else {

			}
		} else {

		}

		// logger.debug("Continue with remaining filters");
		filterChain.doFilter(request, response);

	}

}
