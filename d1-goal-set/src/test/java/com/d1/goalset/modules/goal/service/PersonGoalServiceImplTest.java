package com.d1.goalset.modules.goal.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.d1.goalset.modules.goal.code.EvalWay;
import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.dto.GoalDto.GoalWritingRequest;
import com.d1.goalset.modules.user.domain.GoalSetter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonGoalServiceImplTest {

	@Autowired PersonGoalService personGoalService;
	
	@DisplayName("작성 테스트")
	@Test
	void writeTest() {
		GoalSetter goalSetter = GoalSetter.builder()
										.id((long) 1155991)
										.build(); 
		
		GoalWritingRequest params = GoalWritingRequest.builder()
														.goalName("목표1")
														.evalWayCd(EvalWay.QUANT_EVAL)
														.quantStdMax("테스트")
														.quantStdGoal("목표기준")
														.quantStdMin("최소기준")
														.weight(40)
														.contents("테스트")
														.build();
		
		Goal goal = personGoalService.write(goalSetter, params );
	}

	// 작성 테스트

	// 작성 시 이미 확정상태 테스트

	// 수정 테스트

	// 삭제 테스트
	
	// 테스트는 메소드별로 단위테스트를 진행하는것이 좋음. DB 안붙고 목킹해서 테스트함.
	// 도메인 모델 패턴으로 하면 좋은점은 핵심비즈니스로직이 

}
