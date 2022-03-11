package com.d1.goalset.modules.goal.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.d1.goalset.modules.goal.code.GoalSettingState;
import com.d1.goalset.modules.goal.domain.GoalSetting;
import com.d1.goalset.modules.user.domain.User;

import lombok.Getter;
import lombok.Setter;

public class MemberDto {

	@Getter @Setter
	public static class MemberStatusResponse {
		private Long id;
		private String empNo;
		private String empNm;
		private String orgNm;
		private GoalSettingState goalSettingStateCd;
		
		public static MemberStatusResponse of(User user, GoalSetting goalSetting) {
			MemberStatusResponse response = new MemberStatusResponse();
			response.setId(user.getId());
			response.setEmpNm(user.getEmpNm());
			response.setEmpNo(user.getEmpNo());
			if(user.getOrg() != null) {
				response.setOrgNm(user.getOrg().getOrgNm());
			}
			response.setGoalSettingStateCd(goalSetting.getGoalSettingStateCd());
			
			return response;
		}

		public static List<MemberStatusResponse> of(List<User> users, List<GoalSetting> goalSettings) {
			List<MemberStatusResponse> response = new ArrayList<>();
			
			Map<Long, GoalSetting> goalSettingMap = GoalSetting.createGoalSettingMap(goalSettings); 
			for(User user : users) {
				response.add(MemberStatusResponse.of(user, goalSettingMap.get(user.getId())));
			}
			
			return response;
		}
	}

}
