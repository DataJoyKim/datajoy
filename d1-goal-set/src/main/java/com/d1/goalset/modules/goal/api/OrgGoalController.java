package com.d1.goalset.modules.goal.api;

import java.util.List;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.goalset.modules.goal.api.resource.GoalResource;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalSettingResponse;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.service.OrgGoalService;
import com.d1.goalset.modules.goal.service.query.OrgGoalQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/org-goals/**")
@RequiredArgsConstructor
public class OrgGoalController {

	private final OrgGoalService orgGoalService;
	private final OrgGoalQueryService orgGoalQueryService;
	
	@GetMapping("")
	public ResponseEntity<?> getOrgGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		//접속자가 조직장인지 체크
		List<GoalResponse> goals = orgGoalQueryService.findGoalBy(seasonCd, companyCd, userId);

		GoalResource resource = new GoalResource(goals);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(new GoalResource(resource), HttpStatus.OK);
	}
	
	@GetMapping("/{goalId}")
	public ResponseEntity<?> getOrgGoalsBy(
			@PathVariable Long goalId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		//접속자가 조직장인지 체크
		GoalResponse goal = orgGoalQueryService.findGoalBy(seasonCd, companyCd, userId, goalId);

		GoalResource resource = new GoalResource(goal);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(new GoalResource(resource), HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<?> getOrgGoalStatus(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		//접속자가 조직장인지 체크
		
		GoalSettingResponse goalSetting = orgGoalQueryService.findGoalSettingBy(seasonCd, companyCd, userId);

		GoalResource resource = new GoalResource(goalSetting);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(new GoalResource(resource), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> postOrgGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId,
			@Validated @RequestBody GoalWritingRequest body
			) {
		//접속자가 조직장인지 체크
		
		Long goalId = orgGoalService.write(seasonCd, companyCd, userId, body);

		GoalResource resource = new GoalResource(goalId);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
	@PutMapping("/{goalId}")
	public ResponseEntity<?> putOrgGoalsBy(
			@PathVariable Long goalId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId,
			@Validated @RequestBody GoalWritingRequest body
			) {
		//접속자가 조직장인지 체크
		orgGoalService.update(seasonCd, companyCd, userId, goalId, body);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{goalId}")
	public ResponseEntity<?> deleteOrgGoalsBy(
			@PathVariable Long goalId, 
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		orgGoalService.delete(seasonCd, companyCd, userId, goalId);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/cancel")
	public ResponseEntity<?> cancelOrgGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		orgGoalService.cancel(seasonCd, companyCd, userId);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/submit")
	public ResponseEntity<?> submitOrgGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		orgGoalService.submit(seasonCd, companyCd, userId);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
}
