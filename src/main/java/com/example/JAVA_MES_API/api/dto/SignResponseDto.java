package com.example.JAVA_MES_API.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignResponseDto {
	private boolean success;
	private String signDetailNm;
	private String signDetailUserNm;
	private String signDetailUserId;
	private String signSignEmpCd;
	private String signSignEmpNm;
	private String signTime;
	private byte[] signImage;
}
