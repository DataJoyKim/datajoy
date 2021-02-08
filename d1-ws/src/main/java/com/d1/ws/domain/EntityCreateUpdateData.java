package com.d1.ws.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable 
@Getter @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class EntityCreateUpdateData {
	@Column(name = "reg_date")
    private LocalDateTime regDate;
	
	@Column(name = "mod_date")
    private LocalDateTime modDate;
}
