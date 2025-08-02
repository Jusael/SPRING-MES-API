package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


@Schema(description = "SP 실행 이벤트 DTO")
public class SpExecutionEvent {
	
	@Schema(description = "SP 정보 및 매개변수 DTO", example = "SignDto")
    private Object dto;
	
	@Schema(description = "SP Que Id", example = "1")
    private long queId;

    public SpExecutionEvent(Object  dto, long queId) {
        this.dto = dto;
        this.queId = queId;
    }
    

}