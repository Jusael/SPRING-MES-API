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
    @Column(name = "USER_ID", length = 50, nullable = false, unique = true)
    private String userId;

    @Column(name = "USER_NAME", length = 50)
    private String userName;

    @Column(name = "USER_PASSWORD", length = 50)
    private String userPassword;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "EMPLOYEE_CD", length = 50)
    private String employeeCd;

    @Column(name = "START_DATE", length = 10)
    private String startDate; // YYYY-MM-DD

    @Column(name = "END_DATE", length = 10)
    private String endDate;   // YYYY-MM-DD

    @Column(name = "JWT_TOKEN", length = 500)
    private String jwtToken;

    @Column(name = "FCM_TOKEN", length = 500)
    private String fcmToken;

    @Lob
    @Column(name = "SIGN_IMAGE")
    private byte[] signImage;
}
