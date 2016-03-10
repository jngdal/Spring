package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
// @EnableWebMvcSecurity only spring security 3
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	@Autowired
	PersistentTokenRepository PersistentTokenRepositoryImpl;
	@Autowired
	AuthenticationSuccessHandler AuthenticationSuccessHandler;
	@Autowired
	CustomLogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	@Qualifier("compositeSessionAuthenticationStrategy")
	org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy;

	/*@Autowired
	@Qualifier("concurrentSessionFilter")
	org.springframework.security.web.session.ConcurrentSessionFilter concurrentSessionFilter;*/

	/*
	 * @Autowired
	 * 
	 * @Qualifier("singleEntryFilter") SingleEntryFilter singleEntryFilter;
	 */

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/fb").permitAll().antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/products/**")
				.access("hasRole('ROLE_USER')")
				.antMatchers("/websocket/**")
				.access("hasRole('ROLE_USER')")

				.and().formLogin()
				.loginPage("/login")
				.successHandler(AuthenticationSuccessHandler)
				.usernameParameter("username").passwordParameter("password")
				.and().logout().logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true).and().csrf().and().rememberMe()
				.tokenRepository(PersistentTokenRepositoryImpl)
				.tokenValiditySeconds(1209600).and()
				.apply(new SpringSocialConfigurer());

		// .and().addFilter(concurrentSessionFilter);//.addFilterAfter(singleEntryFilter,
		// ConcurrentSessionFilter.getClass());
		// httpSecurity.sessionManagement().invalidSessionUrl("/results").maximumSessions(1).expiredUrl("/api/users").maxSessionsPreventsLogin(true).and().sessionFixation().changeSessionId();

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				passwordEncoder());

	}

	/*
	 * private CsrfTokenRepository csrfTokenRepository() {
	 * HttpSessionCsrfTokenRepository repository = new
	 * HttpSessionCsrfTokenRepository();
	 * repository.setSessionAttributeName("_csrf"); return repository; }
	 */
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(
				new HttpSessionEventPublisher());
	}

	@Autowired
	@Bean(name = "compositeSessionAuthenticationStrategy")
	org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy getCompositeSessionAuthenticationStrategy(
			@Qualifier("concurrentSessionControlAuthenticationStrategy") ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy,
			@Qualifier("sessionFixationProtectionStrategy") SessionFixationProtectionStrategy sessionFixationProtectionStrategy,
			@Qualifier("registerSessionAuthenticationStrategy") RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy) {

		@SuppressWarnings("serial")
		List<SessionAuthenticationStrategy> sessionAuthenticationStrategy = new ArrayList<SessionAuthenticationStrategy>() {
		};

		sessionAuthenticationStrategy
				.add(concurrentSessionControlAuthenticationStrategy);
		sessionAuthenticationStrategy.add(sessionFixationProtectionStrategy);
		sessionAuthenticationStrategy
				.add(registerSessionAuthenticationStrategy);
		org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy sas = new org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy(
				sessionAuthenticationStrategy);

		return sas;

	}

	@Autowired
	@Bean(name = "concurrentSessionControlAuthenticationStrategy")
	ConcurrentSessionControlAuthenticationStrategy getConcurrentSessionControlAuthenticationStrategy(
			@Qualifier("sessionRegistry") SessionRegistry sessionRegistry) {

		org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy(
				sessionRegistry);
		concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);
		concurrentSessionControlAuthenticationStrategy
				.setExceptionIfMaximumExceeded(true);

		return concurrentSessionControlAuthenticationStrategy;

	}

	@Autowired
	@Bean(name = "registerSessionAuthenticationStrategy")
	RegisterSessionAuthenticationStrategy getRegisterSessionAuthenticationStrategy(
			@Qualifier("sessionRegistry") SessionRegistry sessionRegistry) {

		org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy = new org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy(
				sessionRegistry);

		return registerSessionAuthenticationStrategy;

	}

	@Bean(name = "sessionFixationProtectionStrategy")
	SessionFixationProtectionStrategy getSessionFixationProtectionStrategy() {
		return new SessionFixationProtectionStrategy();
	}

	/*@Autowired
	@Bean(name = "concurrentSessionFilter")
	org.springframework.security.web.session.ConcurrentSessionFilter getConcurrentSessionFilter(
			@Qualifier("sessionRegistry") SessionRegistry sessionRegistry) {
		return new org.springframework.security.web.session.ConcurrentSessionFilter(
				sessionRegistry);
	}*/

	/*
	 * @Bean(name = "singleEntryFilter") SingleEntryFilter
	 * getSingleEntryFilter() { SingleEntryFilter newsingleEntryFilter = new
	 * SingleEntryFilter(); List<String> guardURI = new ArrayList<String>(); //
	 * guardURI.add("/admin/"); guardURI.add("/websocket/"); //
	 * guardURI.add("/products/");
	 * 
	 * newsingleEntryFilter.setGuardURI(guardURI);
	 * newsingleEntryFilter.setRedirectURI("/results"); return
	 * newsingleEntryFilter; }
	 */

	@Bean(name = "sessionRegistry")
	public SessionRegistry getSessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
