package com.table_tracking.table.controller;

import com.table_tracking.common.dto.TableRequest;
import com.table_tracking.table.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8100", "http://10.220.129.4:8100","http://10.238.98.40:8100","*"})
@RequestMapping("/api/table")
public class TabelReqController {

    @Autowired
    private TableService  tableService;

    @PostMapping("/table-requests")
    public ResponseEntity<?> requestTable(@RequestBody TableRequest request) {
        return tableService.requestTable(request);
    }




//    @PostMapping("/send-requests")
//    public ResponseEntity<?> sendRequest(@RequestBody TableRequest request) throws IOException {
//        try {
//            String waiterToken =tableService.getTokenForWaiter(request.getTableId());
//            firebaseNotificationService.sendPushNotification(
//                    waiterToken,
//                    "New Table Request",
//                    "Table " + request.getTableId() + " requested: " + request.getActionType()
//            );
//
//            return ResponseEntity.ok("Request sent successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to send request: " + e.getMessage());
//        }
//    }




//    @PostMapping("/save-token")
//    public ResponseEntity<?> saveToken(@RequestBody Map<String, String> data) {
//        String waiterId = data.get("waiterId");
//        String fcmToken = data.get("token");
//
//        System.out.print("fcmToken"+fcmToken);
//        System.out.print("waiterId"+waiterId);
//
//
////        Waiter waiter = waiterRepository.findById(Long.valueOf(waiterId))
////                .orElse(new Waiter());
//
////        waiter.setId(Long.valueOf(waiterId));
////        waiter.setFcmToken(fcmToken);
//
////        waiterRepository.save(waiter);
////
//        return ResponseEntity.ok("Token saved");
//    }

}
