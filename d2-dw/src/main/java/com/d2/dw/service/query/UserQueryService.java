package com.d2.dw.service.query;

import com.d2.dw.dto.UserDTO;

public interface UserQueryService {

	/**
	 * 
	 * @param userId - 유저 id
	 * @return 유저정보조회
	 */
	UserDTO getUser(Long userId);
	
}
