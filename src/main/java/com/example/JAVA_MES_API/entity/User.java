package com.example.JAVA_MES_API.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_INFO")
@Getter
@Setter
public class User {
	@Id
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String jwtToken;

    @Column(nullable = true)
    private String startDate; // YYYY-MM-DD
    @Column(nullable = true)
    private String endDate;   // YYYY-MM-DD
}