package com.example.JAVA_MES_API.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SpMappingDto {

    private String signCd;        // SIGN_CD varchar(50) PK
    private String spSchema;      // SP_SCHEMA varchar(50)
    private String spName;        // SP_NAME varchar(128)
    private String description;   // DESCRIPTION varchar(256)
    private LocalDateTime createdAt; // CREATED_AT datetime
    private LocalDateTime updatedAt; // UPDATED_AT datetime
}