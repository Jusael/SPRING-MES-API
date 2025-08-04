package com.example.JAVA_MES_API.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.JAVA_MES_API.api.entity.SpExecutionQueue;
import com.example.JAVA_MES_API.api.repository.SpExecutionQueueRepository;

@Service
public class QueueStatusServiceImpl implements QueueStatusService{
	
	SpExecutionQueueRepository spExecutionQueueRepository;
	
	public QueueStatusServiceImpl (SpExecutionQueueRepository spExecutionQueueRepository)
	{
		this.spExecutionQueueRepository = spExecutionQueueRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void UpdateQueStatus(SpExecutionQueue spExecutionQueue) {
		spExecutionQueueRepository.save(spExecutionQueue);
		spExecutionQueueRepository.flush();
	}
	
}
