package com.ecommerce.security.config;

import com.ecommerce.security.jwt.JwtConfigurer;
import com.ecommerce.security.jwt.authorize.AccessDenied;
import com.ecommerce.security.jwt.authorize.JwtAuthenticationEntryPoint;
import com.ecommerce.security.jwt.provider.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenProvider tokenService;
	private final AccessDenied accessDenied;
	private final JwtAuthenticationEntryPoint jwtAuthorization;

	public SecurityConfig(JwtTokenProvider tokenService, AccessDenied accessDenied,
			JwtAuthenticationEntryPoint jwtAuthorization) {
		this.tokenService = tokenService;
		this.accessDenied = accessDenied;
		this.jwtAuthorization = jwtAuthorization;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/auth/**", "/swagger*/**", "/configuration/**", "/webjars/**", "/api-docs/**")
				.permitAll()
				.antMatchers("/api/**").authenticated()
				.and()
				.apply(new JwtConfigurer(tokenService));

		http.exceptionHandling()
		.accessDeniedHandler(accessDenied)
		.authenticationEntryPoint(jwtAuthorization);
	}
}
