package com.d1.goalset.modules.user.api;

import java.util.List;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.d1.goalset.modules.user.dto.UserDto.UserResponse;
import com.d1.goalset.modules.user.service.query.UserQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

	private final UserQueryService userQueryService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable Long userId) {
		UserResponse user = userQueryService.findUserResponse(userId);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}/members")
	public ResponseEntity<?> getMembers(@PathVariable Long userId) {
		List<UserResponse> members = userQueryService.findMembers(userId);

		return new ResponseEntity<>(members, HttpStatus.OK);
	}
}
