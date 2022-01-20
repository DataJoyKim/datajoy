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
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalPlanWritingRequest;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.repository.GoalRepository;
import com.d1.goalset.modules.goal.repository.PersonGoalSettingRepository;
import com.d1.goalset.modules.user.domain.GoalSetter;
import com.d1.goalset.modules.user.repository.GoalSetterRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonGoalServiceImplTest {

	@Autowired PersonGoalService personGoalService;
	@Autowired GoalRepository goalRepository;
	@Autowired GoalSetterRepository userRepository;
	@Autowired PersonGoalSettingRepository goalSettingRepository; 
	
	private final String seasonCd = "202201";
	private final String companyCd = "01";
	
	@DisplayName("작성 테스트")
	@Test
	void writeTest() {
		//given
		GoalSetter goalSetter = getGoalSetter(this.seasonCd, this.companyCd);
		
		Optional<GoalSetter> savedGoalSetter = userRepository.findById((long) 1);
		
		if(savedGoalSetter.isPresent() == false) {
			userRepository.save(goalSetter);
		}
		
		PersonGoalSetting goalSetting = new PersonGoalSetting();
		goalSetting.setGoalSettingStatCd(GoalSettingState.SETTING);
		goalSetting.setGoalSetter(goalSetter);
		goalSetting.setSeasonCd(this.seasonCd);
		goalSetting.setCompanyCd(this.companyCd);
		goalSetting.setGoalType(GoalTypeCode.PERSON_GOAL);
		
		Optional<PersonGoalSetting> savedGoalSetting = goalSettingRepository.findById((long) 1);
		
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
		Long goalId = personGoalService.write(goalSetter, params);
		
		// then
		assertNotNull(goalId);
	}

	private GoalSetter getGoalSetter(String seasonCd, String companyCd) {
		return GoalSetter.builder()
										.id((long) 1)
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
														.id((long) 1)
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
		
		GoalSetter goalSetter = getGoalSetter(this.seasonCd, this.companyCd);
		
		// when
		personGoalService.update((long) 3, goalSetter, params);
		
		// then
	}

	@DisplayName("삭제 테스트")
	@Test
	void deleteTest() {
		// given
		GoalSetter goalSetter = getGoalSetter(this.seasonCd, this.companyCd);
		
		// when
		personGoalService.delete((long) 3, goalSetter);
		
		// then
		Optional<Goal> goal = goalRepository.findById((long) 3);
		assertEquals(goal.get().getGoalWritingStateCd(), GoalWritingState.DELETE);
	}
	
	// 테스트는 메소드별로 단위테스트를 진행하는것이 좋음. DB 안붙고 목킹해서 테스트함.
	// 도메인 모델 패턴으로 하면 좋은점은 핵심비즈니스로직이 

}
