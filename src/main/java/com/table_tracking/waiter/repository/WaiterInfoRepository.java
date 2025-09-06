package com.table_tracking.waiter.repository;

import com.table_tracking.waiter.model.WaiterInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaiterInfoRepository extends MongoRepository<WaiterInfo,String> {


    Optional<WaiterInfo> findByUsername(String username);

    public Optional<WaiterInfo> findByTableIdAndRestaurantId(String tableId,String restaurantId);
}
