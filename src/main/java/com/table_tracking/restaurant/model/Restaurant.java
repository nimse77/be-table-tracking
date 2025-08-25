package com.table_tracking.restaurant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("restaurants")
public class Restaurant {

  @Id
  private String restaurantId;
  private String name;
  private String longitude;
  private String latitude;
  private List<String> menu;
  private List<String> actionType;
  // public org.springframework.data.mongodb.core.geo.GeoJsonPoint location;


  public String getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(String restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public List<String> getMenu() {
    return menu;
  }

  public void setMenu(List<String> menu) {
    this.menu = menu;
  }

  public List<String> getActionType() {
    return actionType;
  }

  public void setActionType(List<String> actionType) {
    this.actionType = actionType;
  }
}

