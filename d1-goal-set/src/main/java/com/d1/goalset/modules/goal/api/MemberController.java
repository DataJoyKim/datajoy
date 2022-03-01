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
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalResponse;
import com.d1.goalset.modules.goal.service.MemberService;
import com.d1.goalset.modules.goal.service.query.MemberQueryService;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/goal/api/v1/members", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final MemberQueryService memberQueryService;
	
	@GetMapping("")
	public ResponseEntity<?> getMembers(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId,
			@RequestParam(required = false) GoalTypeCode goalTypeCode
			) {
		List<UserResponse> members = memberQueryService.findMembers(seasonCd, companyCd, userId, goalTypeCode);
		
		GoalResource resource = new GoalResource(members);
		resource.add(WebMvcLinkBuilder.linkTo(MemberController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMember(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId,
			@RequestParam GoalTypeCode goalTypeCode
			) {
		UserResponse member = memberQueryService.findMember(seasonCd, companyCd, userId, goalTypeCode, memberId);
		
		GoalResource resource = new GoalResource(member);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/{memberId}/goals")
	public ResponseEntity<?> getMemberGoals(
			@PathVariable Long memberId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		List<GoalResponse> goals = memberQueryService.findMembersGoals(seasonCd, companyCd, userId, memberId);
		
		GoalResource resource = new GoalResource(goals);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

	@GetMapping("/{memberId}/goals/{goalId}")
	public ResponseEntity<?> getMemberGoal(
			@PathVariable Long memberId,
			@PathVariable Long goalId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalResponse goal = memberQueryService.findMembersGoal(seasonCd, companyCd, userId, memberId, goalId);
		
		GoalResource resource = new GoalResource(goal);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@PutMapping("/{memberId}/approval")
	public ResponseEntity<?> putMemberApproval(
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
