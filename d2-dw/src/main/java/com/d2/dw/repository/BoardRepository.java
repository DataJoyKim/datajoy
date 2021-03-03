package com.d2.dw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.domain.Board;
import com.d2.dw.repository.querydsl.BoardRepositoryQuerydsl;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryQuerydsl {

}
