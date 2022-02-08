package com.d1.goalset.modules.goal.api;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/goal/api/v1/users", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

	@GetMapping("/owner")
	public ResponseEntity<?> getOwner() {
		return null;
	}
	
	@GetMapping("/members")
	public ResponseEntity<?> getMembers() {
		return null;
	}
}
