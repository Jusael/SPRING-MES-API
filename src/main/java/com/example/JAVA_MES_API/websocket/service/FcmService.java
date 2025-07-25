package com.example.JAVA_MES_API.websocket.service;

import com.example.JAVA_MES_API.api.entity.Alarm;
import com.example.JAVA_MES_API.websocket.entity.AlarmWeb;

public interface FcmService {

	void pushNotification(AlarmWeb alarm);
}
