package com.d1.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d1.ws.domain.Comment;

@Repository("CommentRepository")
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
}
