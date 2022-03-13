package com.d1.goalset.modules.goal.api;

import java.util.List;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.goalset.modules.goal.api.resource.GoalResource;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.dto.MemberDto.MemberStatusResponse;
import com.d1.goalset.modules.goal.service.MemberService;
import com.d1.goalset.modules.goal.service.query.MemberQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/members", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final MemberQueryService memberQueryService;
	
	@GetMapping("/person-goals/status")
	public ResponseEntity<?> getMemberPersonGoalStatus(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		List<MemberStatusResponse> response = memberQueryService.findMembersPersonGoalsStatus(seasonCd, companyCd, userId);
		
		GoalResource resource = new GoalResource(response);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/{memberId}/person-goals")
	public ResponseEntity<?> getMemberPersonGoals(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		List<GoalResponse> goals = memberQueryService.findMembersPersonGoals(seasonCd, companyCd, userId, memberId);
		
		GoalResource resource = new GoalResource(goals);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@GetMapping("/{memberId}/person-goals/{goalId}")
	public ResponseEntity<?> getMemberGoal(
			@PathVariable Long memberId,
			@PathVariable Long goalId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalResponse goal = memberQueryService.findMembersPersonGoal(seasonCd, companyCd, userId, memberId, goalId);
		
		GoalResource resource = new GoalResource(goal);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PutMapping("/{memberId}/person-goals/approval")
	public ResponseEntity<?> putMemberPersonGoalApproval(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		memberService.approve(seasonCd, companyCd, userId, memberId);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/org-goals/status")
	public ResponseEntity<?> getMemberOrgGoalStatus(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		List<MemberStatusResponse> response = memberQueryService.findMembersOrgGoalsStatus(seasonCd, companyCd, userId);
		
		GoalResource resource = new GoalResource(response);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/{memberId}/org-goals")
	public ResponseEntity<?> getMemberOrgGoals(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		List<GoalResponse> goals = memberQueryService.findMembersOrgGoals(seasonCd, companyCd, userId, memberId);
		
		GoalResource resource = new GoalResource(goals);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@GetMapping("/{memberId}/org-goals/{goalId}")
	public ResponseEntity<?> getMemberOrgGoal(
			@PathVariable Long memberId,
			@PathVariable Long goalId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalResponse goal = memberQueryService.findMembersOrgGoal(seasonCd, companyCd, userId, memberId, goalId);
		
		GoalResource resource = new GoalResource(goal);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PutMapping("/{memberId}/org-goals/approval")
	public ResponseEntity<?> putMemberOrgGoalApproval(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		memberService.approve(seasonCd, companyCd, userId, memberId);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
}
