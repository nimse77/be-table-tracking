package com.table_tracking.waiter.repository;

import com.table_tracking.waiter.model.WaiterInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WaiterInfoRepository extends MongoRepository<WaiterInfo,String> {


    public Optional<WaiterInfo> findByWaiterId(String waiterId);

    public Optional<WaiterInfo> findByTableIdAndRestaurantId(String waiterId,String restaurantId);
}
