package com.d1.goalset.modules.goal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.code.GoalWritingState;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalPlanWritingRequest;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonGoalServiceImplTest {

	@Autowired PersonGoalService personGoalService;
	@Autowired GoalRepository goalRepository;
	@Autowired UserRepository userRepository;
	@Autowired GoalSettingRepository goalSettingRepository; 
	
	private final String seasonCd = "202201";
	private final String companyCd = "01";
	
	@DisplayName("작성 테스트")
	@Test
	void writeTest() {
		//given
		User setter = getUser(this.seasonCd, this.companyCd);
		
		Optional<User> savedGoalSetter = userRepository.findById(1L);
		
		if(savedGoalSetter.isPresent() == false) {
			userRepository.save(setter);
		}
		
		GoalSetting goalSetting = GoalSetting.builder()
				.goalSettingStateCd(GoalSettingState.SETTING)
				.seasonCd(seasonCd)
				.companyCd(companyCd)
				.goalType(GoalTypeCode.PERSON_GOAL)
				.build();
		
		Optional<GoalSetting> savedGoalSetting = goalSettingRepository.findById(1L);
		
		if(savedGoalSetting.isPresent() == false) {
			goalSettingRepository.save(goalSetting);
		}
		
		GoalPlanWritingRequest goalPlan = GoalPlanWritingRequest.builder()
															.plan("테스트")
															.endYmd(LocalDate.now())
															.staYmd(LocalDate.now())
															.build();
		
		List<GoalPlanWritingRequest> goalPlans = new ArrayList<>();
		goalPlans.add(goalPlan);
		
		GoalWritingRequest params = GoalWritingRequest.builder()
														.goalName("목표1")
														.evalWayCd(EvalWay.QUANT_EVAL)
														.quantStdMax("테스트")
														.quantStdGoal("목표기준")
														.quantStdMin("최소기준")
														.weight(40)
														.contents("테스트")
														.goalPlans(goalPlans )
														.build();
		// when
		Long goalId = personGoalService.write(seasonCd, companyCd, setter.getId(), params);
		
		// then
		assertNotNull(goalId);
	}

	private User getUser(String seasonCd, String companyCd) {
		return User.builder()
										.id(1L)
										.seasonCd(seasonCd)
										.companyCd(companyCd)
										.isPrimaryAccount(true)
										.isUse(true)
										.build();
	}

	// 작성 시 이미 확정상태 테스트

	@DisplayName("수정 테스트") // 수정 시 목표계획이 조회되고 수정두번일어나는 이슈확인필요
	@Test
	void updateTest() {
		// given
		GoalPlanWritingRequest updateGoalPlan = GoalPlanWritingRequest.builder()
														.id(1L)
														.plan("테스트 수정")
														.endYmd(LocalDate.now())
														.staYmd(LocalDate.now())
														.build();
		
		GoalPlanWritingRequest insertGoalPlan = GoalPlanWritingRequest.builder()
														.plan("신규 테스트 저장")
														.endYmd(LocalDate.now())
														.staYmd(LocalDate.now())
														.build();


		List<GoalPlanWritingRequest> goalPlans = new ArrayList<>();
		goalPlans.add(updateGoalPlan);
		goalPlans.add(insertGoalPlan);
		
		GoalWritingRequest params = GoalWritingRequest.builder()
														.goalName("목표1 수정")
														.evalWayCd(EvalWay.QUANT_EVAL)
														.quantStdMax("테스트 수정")
														.quantStdGoal("목표기준 수정")
														.quantStdMin("최소기준 수정")
														.weight(80)
														.contents("테스트 수정")
														.goalPlans(goalPlans)
														.build();
		// when
		personGoalService.update(seasonCd, companyCd, 1L, 3L, params);
		
		// then
	}

	@DisplayName("삭제 테스트")
	@Test
	void deleteTest() {
		// when
		personGoalService.delete(seasonCd, companyCd, 1L, 3L);
		
		// then
		Optional<Goal> goal = goalRepository.findById(3L);
		assertEquals(goal.get().getGoalWritingStateCd(), GoalWritingState.DELETE);
	}
	
	// 테스트는 메소드별로 단위테스트를 진행하는것이 좋음. DB 안붙고 목킹해서 테스트함.
	// 도메인 모델 패턴으로 하면 좋은점은 핵심비즈니스로직이 

}
