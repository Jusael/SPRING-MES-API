package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Schema(description = "서명 정보 응답 DTO")
public class SignResponseDto {
	
	@Schema(description = "성공여부", example = "true")
	private boolean success;
	
	@Schema(description = "서명 담당자", example = "폐기승인자")
	private String signDetailNm;
	
	@Schema(description = "서명 담당자 명", example = "관리자")
	private String signDetailUserNm;
	
	@Schema(description = "서명 담당자 ID", example = "admin")
	private String signDetailUserId;
	
	@Schema(description = "서명 담당자 사번", example = "1016")
	private String signSignEmpCd;
	
	@Schema(description = "서명 담당자 사번명", example = "admin")
	private String signSignEmpNm;
	
	@Schema(description = "서명 시각", example = "2025-08-02 12:30:17")
	private String signTime;
	
	@Schema(description = "서명 이미지", example = "")
	private byte[] signImage;
}
