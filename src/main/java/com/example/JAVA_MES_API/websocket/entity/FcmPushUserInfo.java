package com.example.JAVA_MES_API.websocket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "USER_INFO")
@Getter
@Setter
@ToString
@NoArgsConstructor 
public class FcmPushUserInfo {

	@Id
    @Column(nullable = false, unique = true)
	private String userId;
	
	private String fcmToken;
}
