package com.example;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//@Configuration
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

	 public SecurityWebApplicationInitializer() {
	        super(SecurityConfiguration.class);
	    }
	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return super.enableHttpSessionEventPublisher();
	}

}
