package com.example.JAVA_MES_API.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.entity.SignRecord;

@Repository
public interface SignRecordRepository extends JpaRepository<SignRecord, Integer> {

    SignRecord findBySignIdAndSignCdAndSignDetailUserId(int signId, String signCd, String signDetailUserId);

}