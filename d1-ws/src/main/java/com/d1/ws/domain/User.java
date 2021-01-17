package com.d1.ws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "seq")
@Entity
@Table(name="user")
public class User {
	@Id
    @GeneratedValue
    @Column(name = "seq")
    private long seq;

    @Column(name="user_id", nullable = false, unique=true)
    private long userId;

    @Column(name="user_nm", nullable = false)
    private String userNm;
    
    @Column(name="role", nullable = false)
    private String role;

    @Column(name="sta_date", nullable = false, unique=true)
    private String staDate;

    @Column(name="end_date", nullable = false)
    private String endDate;
}
