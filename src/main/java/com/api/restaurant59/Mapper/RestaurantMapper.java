package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Model.Entity.Restaurant;
import com.api.restaurant59.Model.Entity.RestaurantType;

import java.util.stream.Collectors;

public class RestaurantMapper {


    // Méthode pour mapper une entité Restaurant vers un DTO RestaurantDTO
    public static RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {

        // Crée un nouveau RestaurantDTO avec les attributs de l'entité Restaurant
        return new RestaurantDTO(

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
                restaurant.getIdAvailability(),

                // Utilise la collection directement sans conversion
                restaurant.getRestaurantTypes(),
                restaurant.getDietaryPreferences(),
                restaurant.getCulinaryOrigins(),
                restaurant.getCulinarySpecialities()
        );
    }


    // Méthode pour mapper un DTO RestaurantDTO vers une entité Restaurant
    public static Restaurant mapToRestaurantEntity(RestaurantDTO restaurantDTO) {

        // Crée une nouvelle entité Restaurant avec les attributs du DTO RestaurantDTO
        Restaurant restaurant = new Restaurant();


        restaurant.setIdRestaurant(restaurantDTO.getIdRestaurant());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setAdditionalAddress(restaurantDTO.getAdditionalAddress());
        restaurant.setPhone(restaurantDTO.getPhone());
        restaurant.setEmail(restaurantDTO.getEmail());
        restaurant.setWebsite(restaurantDTO.getWebsite());
        restaurant.setSiren(restaurantDTO.getSiren());

        //attributs/relation
        restaurant.setIdCity(restaurantDTO.getIdCity());
        restaurant.setIdMichelinCategory(restaurantDTO.getIdMichelinCategory());
        restaurant.setIdAvailability(restaurantDTO.getIdAvailability());

        return restaurant;
    }

}

