package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Model.Entity.Restaurant;

public class RestaurantMapper {

    public static RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {

         RestaurantDTO restaurantDTO = new RestaurantDTO(

                 restaurant.getIdRestaurant(),
                 restaurant.getName(),
                 restaurant.getAddress(),
                 restaurant.getAdditionalAddress(),
                 restaurant.getPhone(),
                 restaurant.getEmail(),
                 restaurant.getWebsite(),
                 restaurant.getSiren(),
                 restaurant.getIdCity(),
                 restaurant.getIdMichelinCategory(),
                 restaurant.getIdAvailability()
         );

        return restaurantDTO;
    }


    public static Restaurant mapToRestaurant(RestaurantDTO restaurantDTO){

        Restaurant restaurant = new Restaurant(

                restaurantDTO.getIdRestaurant(),
                restaurantDTO.getName(),
                restaurantDTO.getAddress(),
                restaurantDTO.getAdditionalAddress(),
                restaurantDTO.getPhone(),
                restaurantDTO.getEmail(),
                restaurantDTO.getWebsite(),
                restaurantDTO.getSiren(),
                restaurantDTO.getIdCity(),
                restaurantDTO.getIdMichelinCategory(),
                restaurantDTO.getIdAvailability()
        );

        return restaurant;
    }


}
