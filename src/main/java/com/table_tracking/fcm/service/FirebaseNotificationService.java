package com.table_tracking.fcm.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
                System.out.println(" Noti" +
                        "fication sent successfully: " + response.getBody());
            } else {
                System.out.println("FCM accepted request but response is unexpected: " + response.getBody());
            }
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("✅ Notification sent: " + response.getBody());

                // 👉 Save notification in Firestore
                Firestore db = FirestoreClient.getFirestore();
                Map<String, Object> notifDoc = new HashMap<>();
                notifDoc.put("title", title);
                notifDoc.put("body", body);
                notifDoc.put("deviceToken", deviceToken);
                notifDoc.put("status", "sent");
                notifDoc.put("createdAt", FieldValue.serverTimestamp());

                db.collection("notifications").add(notifDoc);
            } else {
                System.err.println("Failed: " + response.getStatusCode());
                System.err.println("Response: " + response.getBody());
            }
        } else {
            System.err.println("Failed to send notification. HTTP Status: " + response.getStatusCode());
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
}
