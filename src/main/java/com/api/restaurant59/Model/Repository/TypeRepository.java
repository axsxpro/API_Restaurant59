package com.api.restaurant59.Model.Repository;


import com.api.restaurant59.Model.Entity.RestaurantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<RestaurantType, Integer> {

}
