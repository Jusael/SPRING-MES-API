package com.example.JAVA_MES_API.websocket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
@Table(name = "PUSH_NOTIFICATION")
@Getter
@Setter

public class Alarm {

	@Id
    @Column(nullable = false, unique = true)
	private int appAlarmId;
	@Column(nullable = true)
	private int mesAlarmId;
	@Column(nullable = true)
	private String userId;
	@Column(nullable = true)
	private String userNm;
	@Column(nullable = true)
	private String title;
	@Column(nullable = true)
	private String content1;
	@Column(nullable = true)
	private String content2;
	@Column(nullable = true)
	private String content3;
	@Column(nullable = true)
	private String content4;
	@Column(nullable = true)
	private String content5;
	@Column(nullable = true)
	private String signCd;
	@Column(nullable = true)
	private String signId;
	@Column(nullable = true)
	private String key1;
	@Column(nullable = true)
	private String key2;
	@Column(nullable = true)
	private String key3;
	@Column(nullable = true)
	private String key4;
	@Column(nullable = true)
	private String key5;
	@Column(nullable = true)
	private String sendYn;
	@Column(nullable = true)
	private String sendTime;
	@Column(nullable = true)
	private String createTime;
	@Column(nullable = true)
	private String readYn;
	@Column(nullable = true)
	private String alarmStatus;
	
}
