package com.example.JAVA_MES_API.api.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Schema(description = "SP 매핑DTO")
public class SpMappingDto {

	@Schema(description = "서명 코드", example = "PACKING_ORDER")
    private String signCd;        // SIGN_CD varchar(50) PK
	
	@Schema(description = "SP 스키마명", example = "dbo")
    private String spSchema;      // SP_SCHEMA varchar(50)
	
	@Schema(description = "SP 명", example = "SP_PACKING_ORDER")
    private String spName;        // SP_NAME varchar(128)
	
	@Schema(description = "description", example = "포장지시승인")
    private String description;   // DESCRIPTION varchar(256)
	

}