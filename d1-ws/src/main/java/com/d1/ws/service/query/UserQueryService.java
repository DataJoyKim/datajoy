package com.d1.ws.service.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.d1.ws.service.dto.UserDTO;

public interface UserQueryService {
	UserDTO findUsersById(Long id);

	Page<UserDTO> findAllUsers(PageRequest pageable);
}
