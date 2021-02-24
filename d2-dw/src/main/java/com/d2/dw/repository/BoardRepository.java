package com.d2.dw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
