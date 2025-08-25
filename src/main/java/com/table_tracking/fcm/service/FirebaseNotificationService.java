package com.table_tracking.fcm.service;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class FirebaseNotificationService {

    private static final String PROJECT_ID = "cust-table-track";
    private static final String FCM_API_URL =
            "https://fcm.googleapis.com/v1/projects/" + PROJECT_ID + "/messages:send";
    private static final String SERVICE_ACCOUNT_FILE = "src/main/resources/firebase-service-account.json";


    public void sendPushNotification(String deviceToken, String title, String body) throws IOException {
        String accessToken = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> notification = new HashMap<>();
        notification.put("title", title);
        notification.put("body", body);

        Map<String, Object> message = new HashMap<>();
        message.put("token", deviceToken);
        message.put("notification", notification);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("message", message);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(bodyMap, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(FCM_API_URL, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            if (response.getBody() != null && response.getBody().contains("\"name\"")) {
                System.out.println("✅ Noti" +
                        "fication sent successfully: " + response.getBody());
            } else {
                System.out.println("⚠️ FCM accepted request but response is unexpected: " + response.getBody());
            }
        } else {
            System.err.println("❌ Failed to send notification. HTTP Status: " + response.getStatusCode());
            System.err.println("Response: " + response.getBody());
        }
    }


    private String getAccessToken() throws IOException {
        try (InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json")) {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(serviceAccount)
                    .createScoped(Collections.singletonList("https://www.googleapis.com/auth/firebase.messaging"));
            googleCredentials.refreshIfExpired();
            return googleCredentials.getAccessToken().getTokenValue();
        }
    }

//    private static final String FCM_API_URL = "https://fcm.googleapis.com/fcm/send";
//    private static final String SERVER_KEY = "YOUR_FIREBASE_SERVER_KEY"; // Legacy key

//    public void sendPushNotification(String deviceToken, String title, String body) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(SERVER_KEY);
//
//        Map<String, Object> message = new HashMap<>();
//        message.put("to", deviceToken);
//
//        Map<String, String> notification = new HashMap<>();
//        notification.put("title", title);
//        notification.put("body", body);
//        message.put("notification", notification);
//
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity(FCM_API_URL, request, String.class);
//        System.out.println("FCM Response: " + response.getBody());
//    }
}
