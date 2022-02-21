package com.d1.goalset.modules.goal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.user.domain.User;

public interface GoalSettingRepository extends JpaRepository<GoalSetting, Long> {

	Optional<GoalSetting> findBySetter(User setter);

	Optional<GoalSetting> findBySetterAndGoalType(User setter, GoalTypeCode personGoal);

	List<GoalSetting> findBySeasonCdAndCompanyCdAndApprover(String seasonCd,
			String companyCd, User approver);

}
