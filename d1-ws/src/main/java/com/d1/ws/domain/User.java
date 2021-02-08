package com.d1.ws.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter @Setter @EqualsAndHashCode(of = "id")
@Entity
@Table(name="user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name="email", nullable = false)
    private String email;
    
    @Column(name="group_cd", nullable = false)
    private String groupCd;
    
    @Column(name="password", nullable = false)
    private String password;
    
    @Column(name="user_nm", nullable = false)
    private String userNm;
    
    @Column(name="resident_no", nullable = false)
    private String residentNo;
    
    @Column(name="company")
    private String company;
    
    @Column(name="location")
    private String location;
    
    @Lob
    @Column(name="bio")  
    private String bio;
    
    @Lob
    @Column(name="profile_image") 
    private byte[] profileImage;
    
    @Column(name="phone_no")
    private String phoneNo;
    
    @Column(name="sta_date", nullable = false)
    private LocalDateTime staDate;
    
    @Column(name="end_date", nullable = false)
    private LocalDateTime endDate;

	@Embedded
	private EntityCreateUpdateData entityCreateUpdateData;
}
