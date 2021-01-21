package com.d1.ws.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.ws.domain.User;
import com.d1.ws.service.UserService;

@RestController
@RequestMapping(value="/api/users", produces = "application/hal+json")
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	@GetMapping
	public ResponseEntity<PagedResources<Resource<User>>> getUsers(@RequestParam Map<String, String> params, PagedResourcesAssembler<User> assembler) {
		Integer page = Integer.valueOf(params.get("page"));
		Integer size = Integer.valueOf(params.get("size"));
				
		Page<User> users = userSerivce.findAllUsers(new PageRequest(page, size));
		return ResponseEntity.ok(assembler.toResource(users));
	}
}
