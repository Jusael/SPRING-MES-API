package com.example.JAVA_MES_API.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpExecutionEvent {
	
    private Object dto;
    private long queId;

    public SpExecutionEvent(Object  dto, long queId) {
        this.dto = dto;
        this.queId = queId;
    }
    

}