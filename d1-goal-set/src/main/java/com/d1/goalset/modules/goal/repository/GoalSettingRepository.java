package com.d1.goalset.modules.goal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.goal.domain.PersonGoalSetting;
import com.d1.goalset.modules.user.domain.GoalSetter;

public interface GoalSettingRepository extends JpaRepository<PersonGoalSetting, Long> {

	Optional<PersonGoalSetting> findByGoalSetter(GoalSetter goalSetter);

}
