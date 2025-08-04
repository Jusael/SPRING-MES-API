package com.example.JAVA_MES_API.kafka.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.kafka.entity.KafkaExecutionQueue;

@Repository
public interface KafkaExecutionQueueRepository extends JpaRepository<KafkaExecutionQueue, Long> {

    // 전송 대상 큐 조회 (READY 또는 FAIL + CNT < 3)
    List<KafkaExecutionQueue> findByStatusInAndCntLessThan(List<String> statusList, int maxCnt);

    // Consumer 처리 실패한 메시지 조회
    List<KafkaExecutionQueue> findByConsumedStatus(String consumedStatus);
}
