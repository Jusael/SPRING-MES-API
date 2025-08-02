package com.example.JAVA_MES_API.kafka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.kafka.entity.KafkaMapping;

@Repository
public interface KafkaMappingRepository extends JpaRepository<KafkaMapping, String> {

}
