package com.d1.goalset.modules.user.service.query;

import java.util.List;

import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

public interface UserQueryService {
	UserResponse findUserResponse(Long userId);

	List<UserResponse> findUsers(String seasonCd, String companyCd, List<Long> batchSetterIds);

	List<UserResponse> findMembers(String seasonCd, String companyCd, User approver);

	UserResponse findMember(String seasonCd, String companyCd, User approver, Long memberId);
}
