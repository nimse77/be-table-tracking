package com.table_tracking.waiter.controller;

import com.table_tracking.waiter.model.WaiterInfo;
import com.table_tracking.waiter.service.WaiterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/waiter")
public class WaiterInfoController {

    @Autowired
    private WaiterInfoService waiterInfoService;

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> save(@RequestBody WaiterInfo waiterInfo) {
        String message = waiterInfoService.saveToken(waiterInfo);
        return ResponseEntity.ok(Map.of("message", message));
    }

    @GetMapping("/tokensave/{username}/{fcmToken}")
    public ResponseEntity<Map<String, String>> Token(@PathVariable String username,@PathVariable String fcmToken) {
        WaiterInfo waiterInfo = new WaiterInfo();
        waiterInfo.setUsername(username);
        waiterInfo.setFcmToken(fcmToken);
        String message = waiterInfoService.saveToken(waiterInfo);
        return ResponseEntity.ok(Map.of("message", message));
    }



}
