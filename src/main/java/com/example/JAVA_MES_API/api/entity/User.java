package com.example.JAVA_MES_API.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


//NOTE : 엔터티 구성
//테이블 명과 컬럼에대한 속성부여
//@ID 어노테이션이 있을경우 이를 Key값으로 where 조건절에 사용
//getter, setter를 작성하면 Lom bok으로 자동 get set 메서드 사용가능
@Entity
@Table(name = "USER_INFO")
@Getter
@Setter
public class User {
	@Id
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = true)
    private String userPassword;

    @Column(nullable = true)
    private String jwtToken;

    @Column(nullable = true)
    private String startDate; // YYYY-MM-DD
    @Column(nullable = true)
    private String endDate;   // YYYY-MM-DD
}