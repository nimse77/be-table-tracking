package com.table_tracking.restaurant.service;

import com.table_tracking.restaurant.model.Restaurant;
import com.table_tracking.restaurant.repository.RestaruantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestarunatService {

    @Autowired
    private RestaruantRepository restaruantRepository;

    public Restaurant getRestaruantInfo(String id){
        return restaruantRepository.findById(id).get();
    }

    public List<Restaurant> create(List<Restaurant> restaurant) {
        return restaruantRepository.saveAll(restaurant);
    }

    public Restaurant update(String restaurantId, Restaurant restaurantDetails) {
        return restaruantRepository.findById(restaurantId)
                .map(existing -> {
                    existing.setName(restaurantDetails.getName());
                    existing.setLongitude(restaurantDetails.getLongitude());
                    existing.setLatitude(restaurantDetails.getLatitude());
                    existing.setMenu(restaurantDetails.getMenu());
                    existing.setActionType(restaurantDetails.getActionType());
                    return restaruantRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }


}
