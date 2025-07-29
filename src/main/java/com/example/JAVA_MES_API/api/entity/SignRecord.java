package com.example.JAVA_MES_API.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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
