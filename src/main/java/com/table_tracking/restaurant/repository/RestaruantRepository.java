package com.table_tracking.restaurant.repository;

import com.table_tracking.restaurant.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaruantRepository extends MongoRepository<Restaurant,String> {
    
    public Optional<Restaurant> findById(String id);
}
