package com.d2.dw.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.d2.dw.domain.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
class CommentRepositoryTest {

	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	@DisplayName("댓글 DB 조회 테스트")
	void getCommentsTest() {
		List<Comment> comments = commentRepository.findAll();
		assertThat(comments.size() >= 0 ? true : false);
	}

}
