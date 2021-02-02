package com.d1.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.Project;

@Repository("ProjectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long> {

	Project findById(Long projectId);

}
