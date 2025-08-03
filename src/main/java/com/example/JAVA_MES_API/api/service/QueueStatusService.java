package com.example.JAVA_MES_API.api.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.JAVA_MES_API.api.entity.SpExecutionQueue;

public interface QueueStatusService {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void UpdateQueStatus(SpExecutionQueue spExecutionQueue);
}
