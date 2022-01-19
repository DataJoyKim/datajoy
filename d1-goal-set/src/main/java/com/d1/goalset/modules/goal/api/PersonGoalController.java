package com.d1.goalset.modules.goal.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/person-goals")
@RequiredArgsConstructor
public class PersonGoalController {

	@GetMapping("/")
	public EntityResponse<?> getPersonGoals() {
		return null;
	}
	
	@GetMapping("/{goalCd}")
	public EntityResponse<?> getPersonGoalsBy() {
		return null;
	}
	
	@PostMapping("/")
	public EntityResponse<?> postPersonGoals() {
		return null;
	}
	
	@PutMapping("/{goalCd}")
	public EntityResponse<?> putPersonGoalsBy() {
		return null;
	}
	
	@DeleteMapping("/{goalCd}")
	public EntityResponse<?> deletePersonGoalsBy() {
		return null;
	}
	
	@PutMapping("/cancel")
	public EntityResponse<?> cancelPersonGoals() {
		return null;
	}
	
	@PutMapping("/submit")
	public EntityResponse<?> submitPersonGoals() {
		return null;
	}
}
