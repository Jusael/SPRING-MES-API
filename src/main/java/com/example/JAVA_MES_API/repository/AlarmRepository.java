package com.example.JAVA_MES_API.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.entity.Alarm;
import com.example.JAVA_MES_API.entity.User;

@Repository
public interface AlarmRepository  extends JpaRepository<Alarm, Integer> {

	List<Alarm> findByUserIdAndAlarmStatusIsNull(String userId);
}
