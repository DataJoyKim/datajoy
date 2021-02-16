package com.d1.ws.service.dto;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.d1.ws.domain.EntityCreateUpdateData;
import com.d1.ws.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private Long id;
    private String email;
    private String groupCd;
    private String userNm;
    private String residentNo;
    private String company;
    private String location;
    private String bio;
    private byte[] profileImage;
    private String phoneNo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime staDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endDate;
	
	private EntityCreateUpdateData entityCreateUpdateData;

	public static UserDTO convert(User user) {
		if(user == null) return null;
		
		ModelMapper mapper = new ModelMapper();
		UserDTO userDto = mapper.map(user, UserDTO.class);

		return userDto;
	}

	public static Page<UserDTO> convert(Page<User> users) {
		return users.map(o -> UserDTO.convert(o));
	}
}
