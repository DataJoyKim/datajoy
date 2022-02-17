package com.d1.goalset.modules.goal.api;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.service.MemberService;
import com.d1.goalset.modules.goal.service.query.MemberQueryService;

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
			@RequestParam GoalTypeCode goalTypeCode
			) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping("/{memberId}")
	public ResponseEntity<?> getMember(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId,
			@RequestParam GoalTypeCode goalTypeCode
			) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@GetMapping("/{memberId}/goals")
	public ResponseEntity<?> getMemberGoal(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PutMapping("/{memberId}/approval")
	public ResponseEntity<?> putMemberApproval(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
