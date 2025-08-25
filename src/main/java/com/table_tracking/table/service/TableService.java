package com.table_tracking.table.service;


import com.table_tracking.fcm.service.FirebaseNotificationService;
import com.table_tracking.common.dto.TableRequest;
import com.table_tracking.waiter.service.WaiterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class TableService {

    @Autowired
   private WaiterInfoService waiterInfoService;

    @Autowired
    private FirebaseNotificationService firebaseNotificationService;

    public ResponseEntity<?> requestTable(TableRequest request) {
        System.out.println("Incoming Request: tableId=" + request.getTableId() + ",restaurantId="+ request.getRestaurantId()+", actionType=" + request.getActionType());

        try {
            String waiterToken = waiterInfoService.getTokenForWaiter(request.getTableId(),request.getRestaurantId());
            System.out.println("Resolved Waiter Token: " + waiterToken);

            if (waiterToken == null || waiterToken.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("No token found for waiter of table " + request.getTableId());
            }

            firebaseNotificationService.sendPushNotification(
                    waiterToken,
                    "New Table Request",
                    "Table " + request.getTableId() + " requested: " + request.getActionType()
            );

            return ResponseEntity.ok(Map.of("status", "success"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "error", "message", e.getMessage()));
        }
    }
}
