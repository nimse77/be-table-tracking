package com.table_tracking.waiter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document("waiter_info")
public class WaiterInfo {

  @Id
  @Field("_id")
  private String waiterId;
  private String restaurantId;
  private String tableId;
  private String waiterName;
  private String role;    // WAITER or MANAGER
  private String fcmToken;   // FCM token
  private Instant updatedAt;

  public String getWaiterId() {
    return waiterId;
  }

  public void setWaiterId(String waiterId) {
    this.waiterId = waiterId;
  }

  public String getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(String restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getTableId() {
    return tableId;
  }

  public void setTableId(String tableId) {
    this.tableId = tableId;
  }

  public String getWaiterName() {
    return waiterName;
  }

  public void setWaiterName(String waiterName) {
    this.waiterName = waiterName;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getFcmToken() {
    return fcmToken;
  }

  public void setFcmToken(String fcmToken) {
    this.fcmToken = fcmToken;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}