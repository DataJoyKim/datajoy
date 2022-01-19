package com.d1.goalset.modules.goal.repository.querydsl;

import java.util.Optional;

import com.d1.goalset.modules.goal.domain.Goal;
import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.user.domain.GoalSetter;

public interface GoalRepositoryQuerydsl {

	Optional<Goal> findGoalBy(Long goalId);
	
	Optional<PersonGoalSetting> findPersonGoalSettingBy(GoalSetter goalSetter);
}
