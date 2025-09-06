package com.table_tracking.waiter.service;

import com.table_tracking.common.dto.ApiResponse;
import com.table_tracking.waiter.model.WaiterInfo;
import com.table_tracking.waiter.repository.WaiterInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WaiterInfoService {

    @Autowired
    private WaiterInfoRepository infoRepository;


    public ResponseEntity<?> login(String username){
        return infoRepository.findByUsername(username)
                .map(waiter -> ResponseEntity.ok(
                        new ApiResponse(true, "User found", waiter.getUsername())
                ))
                .orElse(ResponseEntity.status(404).body(
                        new ApiResponse(false, "User not registered", null)));
    }
    public String saveToken(WaiterInfo waiterInfo) {
        WaiterInfo response = infoRepository.findByUsername(waiterInfo.getUsername()).orElse(null);

        if (response != null) {
            response.setFcmToken(waiterInfo.getFcmToken());
            infoRepository.save(response);
            return "Token updated successfully";
        } else {
            waiterInfo.setTableId("table123");
            infoRepository.save(waiterInfo);
            return "Token saved successfully";
        }
    }

    public String getTokenForWaiter(String tableId,String restaurantId) {
        return infoRepository.findByTableIdAndRestaurantId(tableId,restaurantId)
                .map(WaiterInfo::getFcmToken) .orElseThrow(() -> new RuntimeException("Waiter not found for table " + tableId));
    }

}
