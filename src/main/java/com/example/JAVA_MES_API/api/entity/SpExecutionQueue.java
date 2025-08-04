package com.example.JAVA_MES_API.api.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SP_EXECUTION_QUEUE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpExecutionQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUE_ID")
    private Long queId; // PK

    @Column(name = "SP_CD", length = 255, nullable = true)
    private String spCd;

    @Column(name = "SP_SCHEMA", length = 50, nullable = false)
    private String spSchema;

    @Column(name = "SP_NAME", length = 128, nullable = false)
    private String spName;

    @Lob
    @Column(name = "EXEC_PARAMS", nullable = false)
    private String execParams; // SP 실행 파라미터

    @Column(name = "CNT")
    private Integer cnt; // 시도 횟수 (재시도 카운트)

    @Column(name = "STATUS", length = 20)
    private String status; // READY / SUCCESS / FAIL

    @Column(name = "ERROR_MSG")
    private String errorMsg;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.cnt = (this.cnt == null) ? 0 : this.cnt;
    }
}

