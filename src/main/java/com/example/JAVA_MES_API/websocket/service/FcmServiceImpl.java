package com.example.JAVA_MES_API.websocket.service;

import com.example.JAVA_MES_API.websocket.dto.FcmPropertiesDto;
import com.example.JAVA_MES_API.websocket.entity.AlarmWeb;
import com.example.JAVA_MES_API.api.entity.User;
import com.example.JAVA_MES_API.websocket.repository.FcmPushRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;
import okhttp3.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FcmServiceImpl implements FcmService {
	
	private static final Logger log = LoggerFactory.getLogger(FcmServiceImpl.class);
	
    private final FcmPushRepository fcmPushRepository;
    private final FcmPropertiesDto fcmPropertiesDto;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public FcmServiceImpl(FcmPushRepository fcmPushRepository, FcmPropertiesDto fcmPropertiesDto) {
        this.fcmPushRepository = fcmPushRepository;
        this.fcmPropertiesDto = fcmPropertiesDto;
    }

    @Override
    public void pushNotification(AlarmWeb alarm) {
        try {
            String userId = alarm.getUserId();

            User userInfo = fcmPushRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            String fcmToken = userInfo.getFcmToken();
            String accessToken = getAccessToken();

            String fcmUrl = "https://fcm.googleapis.com/v1/projects/" 
                    + fcmPropertiesDto.getProjectId() + "/messages:send";

            // 메시지 JSON 생성
            String jsonBody =  createJson(alarm, fcmToken);

            Request request = new Request.Builder()
                    .url(fcmUrl)
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .addHeader("Content-Type", "application/json; UTF-8")
                    .post(RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8")))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                	log.info("FCM 발송 성공: " + response.body().string());
                } else {
                	log.info("FCM 발송 실패: " + response.code() + " - " + response.body().string());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream(fcmPropertiesDto.getServiceAccountPath()))
                .createScoped(List.of("https://www.googleapis.com/auth/firebase.messaging"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }
    
    
    private String createJson(AlarmWeb alarm, String fcmToken) {
        // 
        Map<String, String> notificationMap = Map.of(
            "title", alarm.getTitle(),
            "body", String.format("%s\n%s\n%s\n%s",
                    alarm.getContent1() != null ? alarm.getContent1() : "",
                    alarm.getContent2() != null ? alarm.getContent2() : "",
                    alarm.getContent3() != null ? alarm.getContent3() : "",
                    alarm.getUserNm() != null ? alarm.getUserNm() : ""
            )
        );

        // data (앱 내부 로직용 key-value)
        Map<String, String> dataMap = Map.ofEntries(
            Map.entry("click_action", "FLUTTER_NOTIFICATION_CLICK"),
            Map.entry("title", alarm.getTitle() != null ? alarm.getTitle() : ""),
            Map.entry("content1", alarm.getContent1() != null ? alarm.getContent1() : ""),
            Map.entry("content2", alarm.getContent2() != null ? alarm.getContent2() : ""),
            Map.entry("content3", alarm.getContent3() != null ? alarm.getContent3() : ""),
            Map.entry("content4", alarm.getContent4() != null ? alarm.getContent4() : ""),
            Map.entry("content5", alarm.getContent5() != null ? alarm.getContent5() : ""),
            Map.entry("appAlarmId", String.valueOf(alarm.getAppAlarmId())),
            Map.entry("userId", alarm.getUserId() != null ? alarm.getUserId() : ""),
            Map.entry("userNm", alarm.getUserNm() != null ? alarm.getUserNm() : ""),
            Map.entry("signCd", alarm.getSignCd() != null ? alarm.getSignCd() : ""),
            Map.entry("signId", alarm.getSignId() != null ? alarm.getSignId() : ""),
            Map.entry("key1", alarm.getKey1() != null ? alarm.getKey1() : ""),
            Map.entry("key2", alarm.getKey2() != null ? alarm.getKey2() : ""),
            Map.entry("key3", alarm.getKey3() != null ? alarm.getKey3() : ""),
            Map.entry("key4", alarm.getKey4() != null ? alarm.getKey4() : ""),
            Map.entry("key5", alarm.getKey5() != null ? alarm.getKey5() : "")
        );

        Map<String, Object> messageMap = Map.of(
            "token", fcmToken,
            "notification", notificationMap,
            "data", dataMap
        );

        Map<String, Object> bodyMap = Map.of("message", messageMap);

        return gson.toJson(bodyMap);
    }
}
