package com.d1.ws.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.ws.domain.User;
import com.d1.ws.service.UserService;

@RestController
@RequestMapping(value="/api/users")
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<List<User>> getUsers(@PathVariable long id, @RequestParam Map<String,Object> params) {
		
		List<User> users = userSerivce.findUsersById(id);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
