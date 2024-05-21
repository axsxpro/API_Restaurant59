package com.api.restaurant59.Model.Repository;


import com.api.restaurant59.Model.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
