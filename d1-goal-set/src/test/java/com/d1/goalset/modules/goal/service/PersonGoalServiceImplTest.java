package com.d1.goalset.modules.goal.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.code.GoalType;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalPlanWritingDto;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.goal.repository.PersonGoalSettingRepository;
import com.d1.goalset.modules.user.domain.GoalSetter;
import com.d1.goalset.modules.user.repository.GoalSetterRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonGoalServiceImplTest {

	@Autowired PersonGoalService personGoalService;
	@Autowired GoalSetterRepository userRepository;
	@Autowired PersonGoalSettingRepository goalSettingRepository; 
	
	@DisplayName("작성 테스트")
	@Test
	void writeTest() {
		String seasonCd = "202201";
		String companyCd = "01";
		
		//given
		GoalSetter goalSetter = GoalSetter.builder()
										.id((long) 1)
										.seasonCd(seasonCd)
										.companyCd(companyCd)
										.isPrimaryAccount(true)
										.isUse(true)
										.build();
		
		Optional<GoalSetter> savedGoalSetter = userRepository.findById((long) 1);
		
		if(savedGoalSetter.isPresent() == false) {
			userRepository.save(goalSetter);
		}
		
		PersonGoalSetting goalSetting = new PersonGoalSetting();
		goalSetting.setGoalSettingStatCd(GoalSettingState.SETTING);
		goalSetting.setGoalSetter(goalSetter);
		goalSetting.setSeasonCd(seasonCd);
		goalSetting.setCompanyCd(companyCd);
		goalSetting.setGoalType(GoalType.PERSON_GOAL);
		
		Optional<PersonGoalSetting> savedGoalSetting = goalSettingRepository.findById((long) 1);
		
		if(savedGoalSetting.isPresent() == false) {
			goalSettingRepository.save(goalSetting);
		}
		
		GoalPlanWritingDto goalPlan = GoalPlanWritingDto.builder()
															.plan("테스트")
															.endYmd(LocalDate.now())
															.staYmd(LocalDate.now())
															.build();
		
		Set<GoalPlanWritingDto> goalPlans = new HashSet<>();
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
		Goal goal = personGoalService.write(goalSetter, params);
		
		// then
		assertNotNull(goal);
	}

	// 작성 테스트

	// 작성 시 이미 확정상태 테스트

	// 수정 테스트

	// 삭제 테스트
	
	// 테스트는 메소드별로 단위테스트를 진행하는것이 좋음. DB 안붙고 목킹해서 테스트함.
	// 도메인 모델 패턴으로 하면 좋은점은 핵심비즈니스로직이 

}
