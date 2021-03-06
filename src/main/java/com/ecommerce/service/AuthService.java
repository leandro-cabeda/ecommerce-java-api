package com.ecommerce.service;

import com.ecommerce.entity.UsersEntity;
import com.ecommerce.exception.RegisterExists;
import com.ecommerce.repository.UsersRepository;
import com.ecommerce.security.dto.AuthDTO;
import com.ecommerce.security.dto.CredentialsDTO;
import com.ecommerce.security.jwt.exception.CredentialsException;
import com.ecommerce.security.jwt.provider.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtService;
	private final UsersRepository repository;

	public AuthService(AuthenticationManager authenticationManager,
			JwtTokenProvider jwtService, UsersRepository repository) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.repository = repository;
	}

	public Object signinUser(AuthDTO auth) {
		
		log.info("Validating the person's authentication with the received credentials!");
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(auth.getUserName(), auth.getPassword()));

			UsersEntity user = repository.findByUserNameIgnoreCase(auth.getUserName());

			String token = jwtService.createToken(user.getUsername(), user.getRoles());

			Map<String, String> obj = new HashMap<>();
			obj.put("username", user.getUsername());
			obj.put("token", token);

			return obj;

		} catch (AuthenticationException e) {
			throw new CredentialsException("Invalid username : " + auth.getUserName() + " or password: "
					+ auth.getPassword() + "provided!");
		}
	}

	public UsersEntity createUser(CredentialsDTO credentials) {
		UsersEntity user = repository.findByUserNameOrEmailIgnoreCase(credentials.getUserName(), credentials.getEmail());

		log.info("Check if the user does exist by username or email");
		if (user != null)
			throw new RegisterExists("Error: record already contained in the bank!");

		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		user = new UsersEntity();
		user.setEmail(credentials.getEmail());
		user.setUserName(credentials.getUserName());
		user.setPassword(encode.encode(credentials.getPassword()));
		
		log.info("Create a new user");
		return repository.save(user);
	}
}
