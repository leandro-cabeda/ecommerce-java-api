package com.ecommerce.service;

import com.ecommerce.entity.UsersEntity;
import com.ecommerce.exception.UsernameNotFoundExcept;
import com.ecommerce.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

	private final UsersRepository repository;

	public UserService(UsersRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersEntity user = repository.findByUserNameIgnoreCase(username);

		log.info("Check if the user does not exist by username");
		if (user == null) {
			throw new UsernameNotFoundExcept("Username: " + username + " of user not found");
		}

		return user;
	}
}
