package com.example.JAVA_MES_API.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.dto.SignResponseDto;
import com.example.JAVA_MES_API.api.entity.Alarm;
import com.example.JAVA_MES_API.api.entity.SignRecord;

@Repository
public interface SignRecordRepository extends JpaRepository<SignRecord, Integer> {

    SignRecord findBySignIdAndSignCdAndSignDetailUserId(int signId, String signCd, String signDetailUserId);

}