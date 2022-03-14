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
@RequestMapping("/api/v1/org-goals/**")
@RequiredArgsConstructor
public class OrgGoalController {

	@GetMapping("/")
	public EntityResponse<?> getOrgGoals() {
		//접속자가 조직장인지 체크
		
		return null;
	}
	
	@GetMapping("/{goalCd}")
	public EntityResponse<?> getOrgGoalsBy() {
		//접속자가 조직장인지 체크
		return null;
	}
	
	@PostMapping("/")
	public EntityResponse<?> postOrgGoals() {
		//접속자가 조직장인지 체크
		return null;
	}
	
	@PutMapping("/{goalCd}")
	public EntityResponse<?> putOrgGoalsBy() {
		//접속자가 조직장인지 체크
		return null;
	}
	
	@DeleteMapping("/{goalCd}")
	public EntityResponse<?> deleteOrgGoalsBy() {
		//접속자가 조직장인지 체크
		return null;
	}
	
	@PutMapping("/cancel")
	public EntityResponse<?> cancelOrgGoals() {
		//접속자가 조직장인지 체크
		return null;
	}
	
	@PutMapping("/submit")
	public EntityResponse<?> submitOrgGoals() {
		//접속자가 조직장인지 체크
		return null;
	}
}
