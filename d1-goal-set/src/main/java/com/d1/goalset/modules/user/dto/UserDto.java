package com.d1.goalset.modules.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.d1.goalset.modules.user.domain.User;

import lombok.Getter;
import lombok.Setter;

public class UserDto {

	@Getter @Setter
	public static class UserResponse {
		private Long id;
		private String empNo;
		private String empNm;
		private String orgNm;
		
		public static UserResponse of(User user) {
			UserResponse response = new UserResponse();
			response.setId(user.getId());
			response.setEmpNm(user.getEmpNm());
			response.setEmpNo(user.getEmpNo());
			if(user.getOrg() != null) {
				response.setOrgNm(user.getOrg().getOrgNm());
			}
			
			return response;
		}

		public static List<UserResponse> of(List<User> users) {
			List<UserResponse> result = new ArrayList<>();
			for(User user : users) {
				result.add(UserResponse.of(user));
			}
			
			return result;
		}
	}
}
