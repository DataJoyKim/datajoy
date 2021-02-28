package com.d2.dw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
