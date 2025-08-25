package com.table_tracking.qrscan.controller;


import com.table_tracking.qrscan.service.GenerateQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:4200","http://13.204.69.86","https://13.204.69.86"})
@RestController
@RequestMapping("/hotel")
public class GenerateQR {

    @Autowired
    private GenerateQRService generateQRService;

    @GetMapping("/generateQR/{restaurantId}/{tableId}")
    public ResponseEntity<byte[]> generateQR(@PathVariable String restaurantId,@PathVariable String tableId) throws Exception {
        return generateQRService.generateQRCode(restaurantId,tableId);
    }


}
