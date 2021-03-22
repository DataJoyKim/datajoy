package com.d2.dw.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d2.dw.dto.UserDTO;
import com.d2.dw.resource.UserResource;
import com.d2.dw.service.query.UserQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaTypes.HAL_JSON_VALUE)
public class UserController {

	private final UserQueryService userQueryService;
	
	@GetMapping("/api/v1/owner")
	public ResponseEntity<?> getOwner() {
		UserDTO user = userQueryService.getUser((long) 1);
		UserResource resource = new UserResource(user);
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
}
