package com.d1.goalset.modules.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findBySeasonCdAndCompanyCdAndIdIn(String seasonCd, String companyCd, List<Long> batchSetterIds);

}
