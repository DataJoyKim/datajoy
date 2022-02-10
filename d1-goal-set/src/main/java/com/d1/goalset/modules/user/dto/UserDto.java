package com.d1.goalset.modules.user.dto;

import org.modelmapper.ModelMapper;

import com.d1.goalset.modules.user.domain.Org;
import com.d1.goalset.modules.user.domain.User;

import lombok.Getter;
import lombok.Setter;

public class UserDto {

	@Getter @Setter
	public static class UserResponse {
		private Long id;
		private String empNo;
		private String empNm;
		private String dutyCd;
		private String positionCd;
		private String jobCd;
		private Org org;
		
		public static UserResponse of(User user) {
			ModelMapper mapper = new ModelMapper();
			UserResponse userResponse = mapper.map(user, UserResponse.class);
			
			return userResponse;
		}
	}
}
