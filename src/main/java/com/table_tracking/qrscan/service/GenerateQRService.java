package com.table_tracking.qrscan.service;

import net.glxn.qrgen.QRCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;


@Service
public class GenerateQRService {


    public ResponseEntity<byte[]> generateQRCode(
             String restaurantId,
             String tableId) throws Exception {

        String baseUrl = "https://3.110.121.122/location-check";
        String data = String.format("%s/%s/%s", baseUrl, restaurantId, tableId);

        ByteArrayOutputStream stream = QRCode.from(data)
                .withSize(300, 300)
                .stream();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(stream.toByteArray(), headers, HttpStatus.OK);
    }

}
