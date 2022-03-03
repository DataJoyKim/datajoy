package com.d1.goalset.modules.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.user.domain.Org;
import com.d1.goalset.modules.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findBySeasonCdAndCompanyCdAndIdIn(String seasonCd, String companyCd, List<Long> batchSetterIds);
	
	List<User> findBySeasonCdAndCompanyCdAndOrgAndIsLeader(String seasonCd, String companyCd, Org org, boolean isLeader);

	List<User> findBySeasonCdAndCompanyCdAndOrgInAndIsLeader(String seasonCd, String companyCd, List<Org> lowerOrgs, boolean isLeader);

	List<User> findBySeasonCdAndCompanyCdAndOrgInAndIsLeaderAndId(String seasonCd, String companyCd, List<Org> lowerOrgs, boolean isLeader, Long id);

	List<User> findBySeasonCdAndCompanyCdAndOrgAndIsLeaderAndId(String seasonCd, String companyCd, Org org, boolean isLeader, Long id);

}
