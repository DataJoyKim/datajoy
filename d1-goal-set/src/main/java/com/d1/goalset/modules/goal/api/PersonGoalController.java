package com.d1.goalset.modules.goal.api;

import java.util.List;

import org.springframework.hateoas.MediaTypes;
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
import com.d1.goalset.modules.goal.service.PersonGoalService;
import com.d1.goalset.modules.goal.service.query.PersonGoalQueryService;
import com.d1.goalset.modules.user.domain.GoalSetter;
import com.d1.goalset.modules.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/person-goals", produces = MediaTypes.HAL_JSON_VALUE)
@RequiredArgsConstructor
public class PersonGoalController {

	private final UserQueryService userQueryService;
	private final PersonGoalService personGoalService;
	private final PersonGoalQueryService personGoalQueryService;
	
	@GetMapping("")
	public ResponseEntity<?> getPersonGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		List<GoalResponse> response = personGoalQueryService.findGoalBy(goalSetter.getId());
		
		GoalResource resource = new GoalResource(response);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}
	
	@GetMapping("/{goalId}")
	public ResponseEntity<?> getPersonGoalsBy(
			@PathVariable Long goalId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		GoalResponse response = personGoalQueryService.findGoalBy(goalSetter.getId(), goalId);
		
		GoalResource resource = new GoalResource(response);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(new GoalResource(resource), HttpStatus.OK);
	}
	
	@GetMapping("/status")
	public ResponseEntity<?> getPersonGoalStatus(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		GoalSettingResponse response = personGoalQueryService.findGoalSettingBy(goalSetter);
		
		GoalResource resource = new GoalResource(response);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());
		
		return new ResponseEntity<>(new GoalResource(resource), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> postPersonGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId,
			@Validated @RequestBody GoalWritingRequest body
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		Long goalId = personGoalService.write(goalSetter, body);

		GoalResource resource = new GoalResource(goalId);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.CREATED);
	}
	
	@PutMapping("/{goalId}")
	public ResponseEntity<?> putPersonGoalsBy(
			@PathVariable Long goalId,
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId,
			@RequestBody GoalWritingRequest body
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		personGoalService.update(goalId, goalSetter, body);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{goalId}")
	public ResponseEntity<?> deletePersonGoalsBy(
			@PathVariable Long goalId, 
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		personGoalService.delete(goalId, goalSetter);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/cancel")
	public ResponseEntity<?> cancelPersonGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		personGoalService.cancel(goalSetter);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/submit")
	public ResponseEntity<?> submitPersonGoals(
			@RequestParam String seasonCd,
			@RequestParam String companyCd,
			@RequestParam Long userId
			) {
		GoalSetter goalSetter = userQueryService.findGoalSetterBy(userId);
		
		personGoalService.submit(goalSetter);

		GoalResource resource = new GoalResource(null);
		resource.add(WebMvcLinkBuilder.linkTo(PersonGoalController.class).withSelfRel());

		return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
	}
}
