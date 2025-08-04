package com.example.JAVA_MES_API.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SIGN_RECORD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SIGN_ID")
    private Integer signId;

    @Column(name = "SIGN_CD", length = 50, nullable = false)
    private String signCd;

    @Column(name = "SIGN_DETAIL_ID")
    private Integer signDetailId;

    @Column(name = "SIGN_DETAIL_SEQ")
    private Integer signDetailSeq;

    @Column(name = "SIGN_DETAIL_NM", length = 50)
    private String signDetailNm;

    @Column(name = "SIGN_DETAIL_USER_ID", length = 50)
    private String signDetailUserId;

    @Column(name = "SIGN_EMP_CD", length = 50)
    private String signEmpCd;

    @Column(name = "SIGN_TIME")
    private LocalDateTime signTime;

    @Lob
    @Column(name = "SIGN_IMAGE")
    private byte[] signImage;
}
