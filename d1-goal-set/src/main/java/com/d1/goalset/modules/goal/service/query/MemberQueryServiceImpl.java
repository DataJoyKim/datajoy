package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import org.springframework.stereotype.Service;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.goal.repository.GoalSettingRepository;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

import lombok.RequiredArgsConstructor;

@Service("MemberQueryService")
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
	
	private final GoalSettingRepository goalSettingRepository;
	
	@Override
	public List<UserResponse> findMembers(String seasonCd, String companyCd, User approver, GoalTypeCode goalTypeCode) {
		
		List<GoalSetting> goalSettingOfMembers = goalSettingRepository.findBySeasonCdAndCompanyCdAndApprover(seasonCd, companyCd, approver);
		
		List<Long> batchSetterIds = GoalSetting.createBatchSetterIds(goalSettingOfMembers);
		
		return null;
	}

}
