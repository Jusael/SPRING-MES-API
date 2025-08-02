package com.example.JAVA_MES_API.kafka.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "KAFKA_MAPPING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaMapping {

    @Id
    @Column(name = "KAFKA_CD", length = 50)
    private String kafkaCd;

    @Column(name = "TOPIC", nullable = false)
    private String topic;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
