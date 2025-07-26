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

    @Query(value = """
        SELECT 	A.SIGN_DETAIL_NM		AS signdetailnm
    		, 	B.USER_NAME 			AS signDetailUserNm
    		,	A.SIGN_DETAIL_USER_ID	AS signDetail_userId
    		, 	A.SIGN_EMP_CD			AS signEmpCd
    		, 	C.USER_NAME 			AS signEmpNm
    		,	A.SIGN_TIME				AS signTime
    		, 	A.SIGN_IMAGE			AS signImage
        FROM SIGN_RECORD A
        INNER JOIN USER_INFO B 
        ON 	B.USER_ID = A.SIGN_DETAIL_USER_ID
        LEFT JOIN USER_INFO C 
        ON C.USER_ID = A.SIGN_EMP_CD
        WHERE A.SIGN_ID = :signId AND A.SIGN_CD = :signCd
        """,
        nativeQuery = true)
    SignResponseDto findSignInfoNative(@Param("signId") Integer signId,
                                      @Param("signCd") String signCd);
    
    SignResponseDto findBySignIdAndSignCdandSignDetailUserId(int signId,String signCd, String signDetailUserId);

}