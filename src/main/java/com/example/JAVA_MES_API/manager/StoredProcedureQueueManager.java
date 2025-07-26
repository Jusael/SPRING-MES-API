package com.example.JAVA_MES_API.manager;

import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;
import com.example.JAVA_MES_API.api.dto.SpMappingDto;
import com.example.JAVA_MES_API.api.entity.SpExecutionQueue;
import com.example.JAVA_MES_API.api.entity.SpMapping;
import com.example.JAVA_MES_API.api.repository.SpExecutionQueueRepository;
import com.example.JAVA_MES_API.api.repository.SpMappingRepository;

import jakarta.persistence.criteria.CriteriaBuilder.Case;

@Service
public class StoredProcedureQueueManager {
	
	private final SpExecutionQueueRepository spExecutionQueueRepository;
	private final SpMappingRepository spMappingRepository;
	
	
	public StoredProcedureQueueManager (SpExecutionQueueRepository spExecutionQueueRepository, SpMappingRepository spMappingRepository)
	{
		this.spExecutionQueueRepository = spExecutionQueueRepository;
		this.spMappingRepository = spMappingRepository;
	}

	
	//TODO : 
	// 1. obj와 연계할 정보를 찾는다.
	// 2. SP에 사용될 매개변수 문자열을 만든다.
	// 3. Q에 데이터를 삽입한다.
	// 4. SP를 실행한다.
	// 5. 성공유무를 저장한다.
	// 6. 3번까지 재실행 이후 더이상 진행하지않는다.
	
	public void callSP(SpExecutionEvent spExecutionEvent)
	{
		 try {
			
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String extractFindKeyInfo(SpExecutionEvent spExecutionEvent) {
		Object dtoObject = spExecutionEvent.getDto();
		
		String SpmappingKey = "";
		
		if(dtoObject instanceof SignRequestDto dto)
		{
			SpmappingKey = dto.getSignCd();
		}
		else {
			throw new IllegalArgumentException("Not Found Matching DTO type");
		}
		
		return SpmappingKey;
	}
}








