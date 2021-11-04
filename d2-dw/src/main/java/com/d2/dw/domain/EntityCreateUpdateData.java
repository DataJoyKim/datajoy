package com.d2.dw.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter @EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor @Builder
public class EntityCreateUpdateData {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@Column(name = "reg_date")
    private LocalDateTime regDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	@Column(name = "mod_date")
    private LocalDateTime modDate;
	
	public static EntityCreateUpdateData createNowDate() {
		return EntityCreateUpdateData.builder()
									.modDate(LocalDateTime.now())
									.regDate(LocalDateTime.now())
									.build();
		
	}
}
