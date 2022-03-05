package com.d1.goalset.modules.user.service;

import java.util.List;

import com.d1.goalset.modules.user.domain.User;

public interface UserService {
	User findUser(Long userId);
	
	User findApprover(Long userId);

	List<User> findMembers(String seasonCd, String companyCd, User approver);

	User findMember(String seasonCd, String companyCd, User approver, Long memberId);
}
