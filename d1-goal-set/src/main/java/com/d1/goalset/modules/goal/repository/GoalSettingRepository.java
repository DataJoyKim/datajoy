package com.d1.goalset.modules.goal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.repository.querydsl.GoalSettingRepositoryQuerydsl;

public interface GoalSettingRepository extends JpaRepository<GoalSetting, Long>, GoalSettingRepositoryQuerydsl {

	Optional<GoalSetting> findBySeasonCdAndCompanyCdAndTargetId(String seasonCd, String companyCd, Long targetId);

	Optional<GoalSetting> findBySeasonCdAndCompanyCdAndTargetIdAndGoalType(String seasonCd, String companyCd, Long targetId, GoalTypeCode personGoal);

}
