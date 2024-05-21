package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Model.Entity.Restaurant;

public class RestaurantMapper {


    //méthode qui convertit une entité Restaurant en un DTO RestaurantDTO
    public static RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {

         RestaurantDTO restaurantDto = new RestaurantDTO(

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

        return restaurantDto;
    }


    //méthode qui convertit un DTO RestaurantDTO en une entité Restaurant
    public static Restaurant mapToRestaurantEntity(RestaurantDTO restaurantDTO){

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
