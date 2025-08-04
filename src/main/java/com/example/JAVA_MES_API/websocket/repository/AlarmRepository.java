package com.example.JAVA_MES_API.websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("wsAlarmRepository")
public interface AlarmRepository  extends JpaRepository<com.example.JAVA_MES_API.websocket.entity.AlarmWeb, Integer> {


}