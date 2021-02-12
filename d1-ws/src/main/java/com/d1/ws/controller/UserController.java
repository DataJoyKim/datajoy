package com.d1.ws.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.ws.controller.resource.UserResource;
import com.d1.ws.service.UserService;
import com.d1.ws.service.dto.UserDTO;
import com.d1.ws.service.query.UserQueryService;
import com.d1.ws.util.PageableUtil;

@RestController
@RequestMapping(produces = "application/hal+json")
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	@Autowired
	private UserQueryService userQuerySerivce;
	
	@GetMapping("/api/v1/users")
	public ResponseEntity<?> getUsers(@RequestParam Map<String, String> params, PagedResourcesAssembler<UserDTO> assembler) {
		Page<UserDTO> users = userQuerySerivce.findAllUsers(PageableUtil.pageable(params));
		return new ResponseEntity<>(assembler.toResource(users, e -> new UserResource(e)), HttpStatus.OK);
	}
}
