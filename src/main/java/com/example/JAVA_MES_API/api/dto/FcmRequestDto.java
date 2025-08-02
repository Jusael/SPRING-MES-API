package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "FCM 토큰 발급 요청 DTO")	
public class FcmRequestDto {

	@Schema(description = "사용자 ID", example = "admin")
	private String userId;
	
	@Schema(description = "FCM 토큰", example = "f7PlCQDuRVWqpe6ACqohu0:APA91bEJKhJnK3CMvBuBcGVIRvvralknOj0a-RC_Lyh4_kfI0K-WHTDw-4ageRasprhvExEoVrIwJWg2a17iqsOasXlxW-ZYoYWZ80UKcX2MLOnKgXDWDnc")
	private String fcmToken;
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getFcmToken() {
		return this.fcmToken;
	}
	
	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}
}
