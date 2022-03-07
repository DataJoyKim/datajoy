package com.d1.goalset.modules.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d1.goalset.modules.user.domain.Org;

public interface OrgRepository extends JpaRepository<Org, Long> {

	List<Org> findByParentOrgId(Long id);


}
