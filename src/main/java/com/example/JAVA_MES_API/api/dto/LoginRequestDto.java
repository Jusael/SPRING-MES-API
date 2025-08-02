package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 정보 요청 DTO")
public class LoginRequestDto {
	
	@Schema(description = "사용자 ID", example = "admin")
    private String userId;
	
	@Schema(description = "PASS WORD", example = "1016")
    private String passWord;

    // 
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // 
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
