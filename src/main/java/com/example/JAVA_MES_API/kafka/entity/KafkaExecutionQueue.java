package com.example.JAVA_MES_API.kafka.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "KAFKA_EXECUTION_QUEUE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaExecutionQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUE_ID")
    private Long queId;

    @Column(name = "TOPIC", nullable = false)
    private String topic;

    @Column(name = "PAYLOAD", nullable = false, columnDefinition = "TEXT")
    private String payload;

    @Column(name = "STATUS", length = 20)
    private String status; // READY / SUCCESS / FAIL / RETRY_SUCCESS

    @Column(name = "ERROR_MSG", columnDefinition = "TEXT")
    private String errorMsg;

    @Column(name = "CNT")
    private Integer cnt;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "SP_QUE_ID")
    private Long spQueId;

    @Column(name = "CONSUMED_STATUS", length = 20)
    private String consumedStatus; // SUCCESS / FAIL

    @Column(name = "CONSUMED_ERROR_MSG", columnDefinition = "TEXT")
    private String consumedErrorMsg;

    @Column(name = "CONSUMED_AT")
    private LocalDateTime consumedAt;

}
