package com.table_tracking.restaurant.controller;


import com.table_tracking.restaurant.model.Restaurant;
import com.table_tracking.restaurant.service.RestarunatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class RestaurantController {

    @Autowired
    private RestarunatService restarunatService;

    @GetMapping("/id/{restaurantId}")
    public ResponseEntity<?> getRestaurantInfo(@PathVariable  String restaurantId){
        Restaurant response=restarunatService.getRestaruantInfo(restaurantId);
     return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<List<Restaurant>> createBulk(@RequestBody List<Restaurant> restaurants) {
        return ResponseEntity.ok(restarunatService.create(restaurants));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable String id,
                                                 @RequestBody Restaurant restaurant) {
        return ResponseEntity.ok(restarunatService.update(id, restaurant));
    }


}
