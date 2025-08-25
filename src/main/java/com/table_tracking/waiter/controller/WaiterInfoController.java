package com.table_tracking.waiter.controller;

import com.table_tracking.waiter.model.WaiterInfo;
import com.table_tracking.waiter.service.WaiterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/waiter")
public class WaiterInfoController {

    @Autowired
    private WaiterInfoService waiterInfoService;

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveToken(@RequestBody WaiterInfo waiterInfo) {
        String message = waiterInfoService.saveToken(waiterInfo);
        return ResponseEntity.ok(Map.of("message", message));
    }



}
