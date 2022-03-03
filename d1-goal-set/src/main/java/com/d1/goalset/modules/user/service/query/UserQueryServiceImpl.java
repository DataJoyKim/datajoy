package com.d1.goalset.modules.user.service.query;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d1.goalset.common.exception.BusinessException;
import com.d1.goalset.modules.user.domain.Org;
import com.d1.goalset.modules.user.domain.User;
import com.d1.goalset.modules.user.dto.UserDto.UserResponse;
import com.d1.goalset.modules.user.error.UserErrorCode;
import com.d1.goalset.modules.user.repository.OrgRepository;
import com.d1.goalset.modules.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service("UserQueryService")
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService{

	private final UserRepository userRepository;
	private final OrgRepository orgRepository;

	@Override
	public UserResponse findUserResponse(Long userId) {
		return UserResponse.of(userRepository.findById(userId).orElseThrow(() -> new BusinessException(UserErrorCode.NULL_GOAL_SETTER)));
	}

	@Override
	public List<UserResponse> findUsers(String seasonCd, String companyCd, List<Long> batchSetterIds) {
		return UserResponse.of(userRepository.findBySeasonCdAndCompanyCdAndIdIn(seasonCd, companyCd, batchSetterIds));
	}

	@Override
	public List<UserResponse> findMembers(String seasonCd, String companyCd, User approver) {
		Org org = Optional.of(approver.getOrg())
							.orElseThrow(() -> new BusinessException(UserErrorCode.NOT_FOUND_OWN_ORG));

		List<User> members = new ArrayList<>(); 
		
		List<Org> lowerOrgs = orgRepository.findBySeasonCdAndCompanyCdAndParentOrgId(seasonCd, companyCd, org.getId());
		if(hasLowerOrg(lowerOrgs)) {
			List<User> lowerOrgLeaders = userRepository.findBySeasonCdAndCompanyCdAndOrgInAndIsLeader(seasonCd, companyCd, lowerOrgs, true);
			
			//TODO 한 조직에 조직장이 여려명인 경우 처리필요 
			
			members.addAll(lowerOrgLeaders);
		}
		
		List<User> myOrgMembers = userRepository.findBySeasonCdAndCompanyCdAndOrgAndIsLeader(seasonCd, companyCd, approver.getOrg(), false);
		
		members.addAll(myOrgMembers);
		
		return UserResponse.of(members);
	}

	private boolean hasLowerOrg(List<Org> lowerOrgs) {
		return lowerOrgs.size() > 0;
	}

	@Override
	public UserResponse findMember(String seasonCd, String companyCd, User approver, Long memberId) {
		Org org = Optional.of(approver.getOrg())
							.orElseThrow(() -> new BusinessException(UserErrorCode.NOT_FOUND_OWN_ORG));

		List<User> members = new ArrayList<>(); 

		List<Org> lowerOrgs = orgRepository.findBySeasonCdAndCompanyCdAndParentOrgId(seasonCd, companyCd, org.getId());
		if(hasLowerOrg(lowerOrgs)) {
			
			List<User> lowerOrgLeaders = userRepository.findBySeasonCdAndCompanyCdAndOrgInAndIsLeaderAndId(seasonCd, companyCd, lowerOrgs, true, memberId);
		
			//TODO 한 조직에 조직장이 여려명인 경우 처리필요 
		
			members.addAll(lowerOrgLeaders);
		}
		
		List<User> myOrgMembers = userRepository.findBySeasonCdAndCompanyCdAndOrgAndIsLeaderAndId(seasonCd, companyCd, approver.getOrg(), false, memberId);
		
		members.addAll(myOrgMembers);
		
		return (members.size() > 0) ? UserResponse.of(members.get(0)) : null;
	}
}
