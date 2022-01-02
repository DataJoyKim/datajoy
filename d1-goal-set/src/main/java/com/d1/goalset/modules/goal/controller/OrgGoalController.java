package com.d1.goalset.modules.goal.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/goal-goals/**")
@RequiredArgsConstructor
public class OrgGoalController {

	@GetMapping("")
	public EntityResponse<?> getOrgGoals() {
		return null;
	}
	
	@GetMapping("/{goalCd}")
	public EntityResponse<?> getOrgGoalsBy() {
		return null;
	}
	
	@PostMapping("")
	public EntityResponse<?> postOrgGoals() {
		return null;
	}
	
	@PutMapping("/{goalCd}")
	public EntityResponse<?> putOrgGoalsBy() {
		return null;
	}
	
	@DeleteMapping("/{goalCd}")
	public EntityResponse<?> deleteOrgGoalsBy() {
		return null;
	}
	
	@PutMapping("/cancel")
	public EntityResponse<?> cancelOrgGoals() {
		return null;
	}
	
	@PutMapping("/submit")
	public EntityResponse<?> submitOrgGoals() {
		return null;
	}
}
