package com.example.JAVA_MES_API.api.service;

import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;

public interface SpQueueService {
	long createAndPublish(Object dto);

	void callSP(SpExecutionEvent spExecutionEvent);

}
