package com.table_tracking.fcm.controller;

import com.table_tracking.fcm.service.FirebaseNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/fcm")
public class FirebaseNotificationController {


    @Autowired
    private FirebaseNotificationService firebaseNotificationService;

    @PostMapping("/notification")
    public void sendPushNotification(String deviceToken, String title, String body) throws IOException {
        firebaseNotificationService.sendPushNotification(deviceToken,title,body);
    }
}
