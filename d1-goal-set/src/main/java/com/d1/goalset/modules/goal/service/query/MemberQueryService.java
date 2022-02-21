package com.d1.goalset.modules.goal.service.query;

import java.util.List;

import com.d1.goalset.modules.goal.code.GoalTypeCode;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;

public interface MemberQueryService {

	List<UserResponse> findMembers(String seasonCd, String companyCd, User approver, GoalTypeCode goalTypeCode);

}
