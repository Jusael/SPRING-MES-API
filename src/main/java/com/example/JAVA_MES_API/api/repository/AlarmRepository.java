package com.example.JAVA_MES_API.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.entity.Alarm;

@Repository
public interface AlarmRepository  extends JpaRepository<Alarm, Integer> {

	List<Alarm> findByUserIdAndAlarmStatusIsNull(String userId);
}
