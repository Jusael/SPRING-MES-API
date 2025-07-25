package com.example.JAVA_MES_API.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SP_MAPPING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpMapping {

    @Id
    @Column(name = "SP_CD", length = 50, nullable = false)
    private String spCd;  // PK

    @Column(name = "SP_SCHEMA", length = 50)
    private String spSchema;

    @Column(name = "SP_NAME", length = 128)
    private String spName;

    @Column(name = "DESCRIPTION", length = 256)
    private String description;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
