package com.d2.dw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
