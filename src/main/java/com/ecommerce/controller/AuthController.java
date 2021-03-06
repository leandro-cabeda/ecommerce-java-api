package com.ecommerce.controller;

import com.ecommerce.controller.helpers.UsersHelper;
import com.ecommerce.dto.UsersDTO;
import com.ecommerce.security.dto.AuthDTO;
import com.ecommerce.security.dto.CredentialsDTO;
import com.ecommerce.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = { "Authentication EndPoint" })
public class AuthController {

	private final AuthService service;
	private UsersHelper helper;

	public AuthController(AuthService service, UsersHelper helper) {
		this.service = service;
		this.helper = helper;
	}

	@PostMapping(value = "/signin", produces = "application/json", consumes = "application/json")
	@ApiOperation(value = "Authenticates a user with credentials")
	public ResponseEntity<Object> signinUser(@Valid @RequestBody AuthDTO authDTO, BindingResult result) {

		log.info("Checks if credentials error has occurred");
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(authDTO);

		log.info("Confirms user authentication and returns token for access to other API routes");
		Object credentials = service.signinUser(authDTO);

		return ResponseEntity.ok(credentials);
	}

	@PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
	@ApiOperation(value = "Creates a new user")
	public ResponseEntity<?> createUser(@Valid @RequestBody CredentialsDTO credentialsDTO, BindingResult result) {

		log.info("Checks if the information is correct by the validation implemented!");
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(credentialsDTO);

		log.info("Converts data to entity when sending through the service and returns converting to DTO");
		UsersDTO user = helper.toModel(service.createUser(credentialsDTO));

		log.info("Adds the url's URI with Id!");
		URI uri = getUri(user.getId());

		return ResponseEntity.created(uri).body(user);
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}
}
