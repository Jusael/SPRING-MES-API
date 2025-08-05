package com.example.JAVA_MES_API.websocket.service;

import com.example.JAVA_MES_API.api.entity.User;
import com.example.JAVA_MES_API.websocket.dto.FcmPropertiesDto;
import com.example.JAVA_MES_API.websocket.entity.AlarmWeb;
import com.example.JAVA_MES_API.websocket.repository.FcmPushRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class FcmServiceImpl implements FcmService {

    private static final Logger log = LoggerFactory.getLogger(FcmServiceImpl.class);

    private static final String FCM_URL_TEMPLATE = "https://fcm.googleapis.com/v1/projects/%s/messages:send";
    private static final String FIREBASE_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");

    private final FcmPushRepository fcmPushRepository;
    private final FcmPropertiesDto fcmPropertiesDto;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    // AccessToken 캐싱용
    private String cachedAccessToken;
    private Instant accessTokenExpiry;

    public FcmServiceImpl(FcmPushRepository fcmPushRepository, FcmPropertiesDto fcmPropertiesDto) {
        this.fcmPushRepository = fcmPushRepository;
        this.fcmPropertiesDto = fcmPropertiesDto;
    }

    @Override
    public void pushNotification(AlarmWeb alarm) {
        try {
            String fcmToken = getFcmTokenForUser(alarm.getUserId());
            String accessToken = getAccessToken();

            String fcmUrl = String.format(FCM_URL_TEMPLATE, fcmPropertiesDto.getProjectId());
            String jsonBody = createJson(alarm, fcmToken);

            Request request = buildFcmRequest(fcmUrl, accessToken, jsonBody);
            sendFcmRequest(request, alarm.getUserId());

        } catch (Exception e) {
            log.error("FCM 발송 예외 발생 - userId: {}", alarm.getUserId(), e);
            // TODO: 실패 큐 저장 로직 추가 가능
        }
    }

    private String getFcmTokenForUser(String userId) {
        return fcmPushRepository.findById(userId)
                .map(User::getFcmToken)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    }

    private String getAccessToken() throws IOException {
        // 캐시된 토큰이 유효하면 재사용
        if (cachedAccessToken != null && accessTokenExpiry != null && Instant.now().isBefore(accessTokenExpiry)) {
            return cachedAccessToken;
        }

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream(fcmPropertiesDto.getServiceAccountPath()))
                .createScoped(List.of(FIREBASE_SCOPE));

        googleCredentials.refreshIfExpired();
        cachedAccessToken = googleCredentials.getAccessToken().getTokenValue();
        accessTokenExpiry = googleCredentials.getAccessToken().getExpirationTime().toInstant();

        return cachedAccessToken;
    }

    private Request buildFcmRequest(String url, String accessToken, String jsonBody) {
        return new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + accessToken)
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .post(RequestBody.create(jsonBody, JSON_MEDIA_TYPE))
                .build();
    }

    private void sendFcmRequest(Request request, String userId) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            String respBody = response.body() != null ? response.body().string() : "";

            if (response.isSuccessful()) {
                log.info("FCM 발송 성공 - userId: {}, 응답: {}", userId, respBody);
            } else {
                log.warn("FCM 발송 실패 - userId: {}, 상태: {}, 응답: {}", userId, response.code(), respBody);
                // TODO: 실패 큐 저장 로직 추가 가능
            }
        }
    }

    private String createJson(AlarmWeb alarm, String fcmToken) {
        Map<String, String> notificationMap = Map.of(
                "title", alarm.getTitle(),
                "body", String.format("%s\n%s\n%s\n%s",
                        nullToEmpty(alarm.getContent1()),
                        nullToEmpty(alarm.getContent2()),
                        nullToEmpty(alarm.getContent3()),
                        nullToEmpty(alarm.getUserNm()))
        );

        Map<String, String> dataMap = Map.ofEntries(
                Map.entry("click_action", "FLUTTER_NOTIFICATION_CLICK"),
                Map.entry("title", nullToEmpty(alarm.getTitle())),
                Map.entry("content1", nullToEmpty(alarm.getContent1())),
                Map.entry("content2", nullToEmpty(alarm.getContent2())),
                Map.entry("content3", nullToEmpty(alarm.getContent3())),
                Map.entry("content4", nullToEmpty(alarm.getContent4())),
                Map.entry("content5", nullToEmpty(alarm.getContent5())),
                Map.entry("appAlarmId", String.valueOf(alarm.getAppAlarmId())),
                Map.entry("userId", nullToEmpty(alarm.getUserId())),
                Map.entry("userNm", nullToEmpty(alarm.getUserNm())),
                Map.entry("signCd", nullToEmpty(alarm.getSignCd())),
                Map.entry("signId", nullToEmpty(alarm.getSignId())),
                Map.entry("key1", nullToEmpty(alarm.getKey1())),
                Map.entry("key2", nullToEmpty(alarm.getKey2())),
                Map.entry("key3", nullToEmpty(alarm.getKey3())),
                Map.entry("key4", nullToEmpty(alarm.getKey4())),
                Map.entry("key5", nullToEmpty(alarm.getKey5()))
        );

        Map<String, Object> messageMap = Map.of(
                "token", fcmToken,
                "notification", notificationMap,
                "data", dataMap
        );

        return gson.toJson(Map.of("message", messageMap));
    }

    private String nullToEmpty(String value) {
        return value != null ? value : "";
    }
}
