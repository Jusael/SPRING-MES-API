package com.example.JAVA_MES_API.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.entity.SpExecutionQueue;


@Repository
public interface SpExecutionQueueRepository extends JpaRepository<SpExecutionQueue, Long> {
  /*  User findByUserId(String userId);
    
    //Modifying 예시
    @Modifying
    @Query("UPDATE User u SET u.jwtToken = :token WHERE u.userId = :userId")
    int updateJwtToken(@Param("userId") String userId, @Param("token") String token);*/
}