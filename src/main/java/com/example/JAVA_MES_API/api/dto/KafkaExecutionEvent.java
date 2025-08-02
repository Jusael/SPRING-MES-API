package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Kafka 전송 이벤트 DTO")
public class KafkaExecutionEvent {

    @Schema(description = "Kafka 메시지 본문 DTO", example = "AlarmDto")
    private Object dto;
    

    @Schema(description = "Kafka 큐 ID", example = "1001")
    private long queId;
    

    @Schema(description = "Sp 큐 ID", example = "1001")
    private long spQueId;


    public KafkaExecutionEvent(Object dto, long queId, long spQueId) {
        this.dto = dto;
        this.queId = queId;
        this.spQueId = spQueId;
    }
}
