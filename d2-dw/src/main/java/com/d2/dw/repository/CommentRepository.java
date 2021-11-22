package com.d2.dw.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.d2.dw.domain.Board;
import com.d2.dw.domain.Comment;
import com.d2.dw.domain.User;
import com.d2.dw.repository.querydsl.CommentRepositoryQuerydsl;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryQuerydsl {

	Page<Comment> findByBoard(Board board, Pageable pageable);

	Optional<Comment> findByIdAndUser(Long commentId, User writer);

	Optional<Comment> findByIdAndUserAndBoard(Long commentId, User writer, Board board);

}
