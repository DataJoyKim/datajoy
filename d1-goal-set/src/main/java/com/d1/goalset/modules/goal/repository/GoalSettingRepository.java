package com.d1.goalset.modules.goal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.goal.domain.GoalSetting;

public interface GoalSettingRepository extends JpaRepository<GoalSetting, Long> {

}
