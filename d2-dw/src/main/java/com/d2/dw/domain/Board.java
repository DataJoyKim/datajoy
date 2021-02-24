package com.d2.dw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "board")
public class Board {
	@Id @GeneratedValue
	@Column(name = "board_no")
	private Long id;
	
	@Column(name = "name")
	private String name; 
}
