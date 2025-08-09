# MES API (Spring Boot)

Spring Boot 기반의 MES 백엔드 API입니다.  
실시간 알림(WebSocket), 재처리 큐(SP Queue, Kafka Queue), JWT 인증 등 현장 중심 기능을 포함하고 있습니다.

---

## 기술 스택
- Spring Boot 3.2.x, Gradle  
- JPA + MyBatis  
- MySQL + Stored Procedure  
- WebSocket (Handshake 단계 JWT 검증)  
- Kafka (Producer) + DLQ(이력 관리)  
- Swagger / springdoc-openapi

---

## 주요 모듈
- **Auth**: JWT 발급 및 검증
- **Alarm**: 알림 수신, 저장, WebSocket 전송
- **Sign**: 전자서명 요청 및 SP 호출 처리
- **Queue**: SP Queue/Kafka Queue 재처리
- **Kafka**: 메시지 발송 서비스
- **WebSocket**: 세션 관리, 메시지 브로드캐스트

---

## 엔드포인트 예시
| 기능 | 메서드 | 경로 |
|---|---|---|
| 로그인(JWT) | POST | `/api/user/login` |
| 알림 수신 | POST | `/api/alarm/receive` |
| 전자서명 실행 | POST | `/api/sign/execute` |
| 서명 상세 조회 | GET | `/api/sign/detail` |
| 알림 재전송 | POST | `/api/alarm/retry` |
| Kafka 메시지 발송 | POST | `/api/kafka/send` |


---

## 동작 개요
- **실시간 알림**: 서버 이벤트 발생 시 WebSocket 세션 조회 후 즉시 전송  
- **전자서명 처리**: 요청 수신 → SP 실행 → 결과를 큐 테이블에 기록  
- **재처리 로직**  
  - SP Queue: 실패 건을 큐에 저장 후 최대 3회 비동기 재실행  
  - Kafka Queue: 발송 실패 시 큐 적재 후 재발송 시도, Consumer에서 처리 결과 기록  
  - DLQ: 최종 실패 건을 보관하여 이력 관리

---

## 설계 특징
- 이벤트 기반 비동기 처리로 메인 트랜잭션과 분리  
- `@Retryable`을 활용한 최대 3회 자동 재시도  
- 큐 상태, 오류 메시지, 재시도 횟수를 기록해 추적 가능  
- DLQ는 최종 실패 건을 이력 관리 용도로 활용
