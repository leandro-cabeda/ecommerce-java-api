package com.ecommerce.security.jwt.filter;

import com.ecommerce.security.jwt.provider.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider tokenService;

	public JwtTokenFilter(JwtTokenProvider tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = tokenService.resolveTokenHeaderRequest((HttpServletRequest) request);

		if (token != null && tokenService.validateToken(token)) {
			Authentication auth = tokenService.getAuthentication(token);

			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		chain.doFilter(request, response);
	}
}
