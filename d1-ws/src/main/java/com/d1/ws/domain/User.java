package com.d1.ws.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Id @GeneratedValue
    @Column(name = "id")
    private long id;
    
    @Column(name="email", unique = true, nullable = false)
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
    
    @Column(name="bio", columnDefinition="BLOB")  
    private String bio;
    
    @Column(name="profile_image", columnDefinition="BLOB") 
    private String profileImage;
    
    @Column(name="phone_no")
    private String phoneNo;
    
    @Column(name="sta_date", nullable = false, columnDefinition = "DATETIME")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime staDate;
    
    @Column(name="end_date", nullable = false, columnDefinition = "DATETIME")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime endDate;
    
    @Column(name="reg_user_id")
    private Long regUserId;
    
    @Column(name="reg_date", columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    
    @Column(name="mod_user_id")
    private Long modUserId;
    
    @Column(name="mod_date", columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modDate;
}
