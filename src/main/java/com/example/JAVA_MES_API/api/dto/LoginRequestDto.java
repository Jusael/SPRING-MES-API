package com.example.JAVA_MES_API.api.dto;

public class LoginRequestDto {
    private String userId;
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
