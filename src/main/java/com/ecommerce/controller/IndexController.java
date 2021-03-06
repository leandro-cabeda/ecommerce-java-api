package com.ecommerce.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@Api(tags = { "Index EndPoint" })
public class IndexController {

	@GetMapping(produces = "application/json")
	@ApiOperation(value = "Initial presentation of welcome to the Ecommerce API")
	public ResponseEntity<String> welcome() {

		return ResponseEntity.ok("Welcome to the Ecommerce API");
	}
}
