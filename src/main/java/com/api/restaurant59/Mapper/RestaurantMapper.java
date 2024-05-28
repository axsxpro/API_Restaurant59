package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Model.Entity.Restaurant;


import java.util.HashSet;
import java.util.stream.Collectors;

public class RestaurantMapper {


    // Méthode pour mapper une entité Restaurant vers un DTO RestaurantDTO
    public static RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {

        // Crée un nouveau RestaurantDTO avec les attributs de l'entité Restaurant
        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setIdRestaurant(restaurant.getIdRestaurant());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setAdditionalAddress(restaurant.getAdditionalAddress());
        restaurantDTO.setPhone(restaurant.getPhone());
        restaurantDTO.setEmail(restaurant.getEmail());
        restaurantDTO.setWebsite(restaurant.getWebsite());
        restaurantDTO.setSiren(restaurant.getSiren());

        // Mappe les entités many to one
        restaurantDTO.setCity(CityMapper.mapToCityDTO(restaurant.getIdCity()));
        restaurantDTO.setMichelinCategory(MichelinCategoryMapper.mapToMichelinCategoryDTO(restaurant.getIdMichelinCategory()));
        restaurantDTO.setAvailability(AvailabilityMapper.mapToAvailabilityDTO(restaurant.getIdAvailability()));

        // Mappe les collections d'entités many to many
        if (restaurant.getRestaurantTypes() != null && !restaurant.getRestaurantTypes().isEmpty()) {
            restaurantDTO.setRestaurantTypes(restaurant.getRestaurantTypes().stream()
                    .map(RestaurantTypeMapper::mapToRestaurantTypeDTO)
                    .collect(Collectors.toSet()));
        } else {
            restaurantDTO.setRestaurantTypes(new HashSet<>());
        }

        if (restaurant.getDietaryPreferences() != null && !restaurant.getDietaryPreferences().isEmpty()) {
            restaurantDTO.setDietaryPreferences(restaurant.getDietaryPreferences().stream()
                    .map(DietaryPreferenceMapper::mapToDietaryPreferenceDTO)
                    .collect(Collectors.toSet()));
        } else {
            restaurantDTO.setDietaryPreferences(new HashSet<>());
        }

        if (restaurant.getCulinaryOrigins() != null && !restaurant.getCulinaryOrigins().isEmpty()) {
            restaurantDTO.setCulinaryOrigins(restaurant.getCulinaryOrigins().stream()
                    .map(CulinaryOriginMapper::mapToCulinaryOriginDTO)
                    .collect(Collectors.toSet()));
        } else {
            restaurantDTO.setCulinaryOrigins(new HashSet<>());
        }

        if (restaurant.getCulinarySpecialities() != null && !restaurant.getCulinarySpecialities().isEmpty()) {
            restaurantDTO.setCulinarySpecialities(restaurant.getCulinarySpecialities().stream()
                    .map(CulinarySpecialityMapper::mapToCulinarySpecialityDTO)
                    .collect(Collectors.toSet()));
        } else {
            restaurantDTO.setCulinarySpecialities(new HashSet<>());
        }

        return restaurantDTO;
    }




    // Méthode pour mapper un DTO RestaurantDTO vers une entité Restaurant
    public static Restaurant mapToRestaurantEntity(RestaurantDTO restaurantDto) {

        // Crée une nouvelle entité Restaurant avec les attributs du DTO RestaurantDTO
        Restaurant restaurant = new Restaurant();

        restaurant.setIdRestaurant(restaurantDto.getIdRestaurant());
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setAdditionalAddress(restaurantDto.getAdditionalAddress());
        restaurant.setPhone(restaurantDto.getPhone());
        restaurant.setEmail(restaurantDto.getEmail());
        restaurant.setWebsite(restaurantDto.getWebsite());
        restaurant.setSiren(restaurantDto.getSiren());

        // Mappe les entités many to one
        restaurant.setIdCity(CityMapper.mapToCityEntity(restaurantDto.getCity()));
        restaurant.setIdMichelinCategory(MichelinCategoryMapper.mapToMichelinCategoryEntity(restaurantDto.getMichelinCategory()));
        restaurant.setIdAvailability(AvailabilityMapper.mapToAvailabilityEntity(restaurantDto.getAvailability()));

        // Mappe les collections d'entités many to many
        if (restaurantDto.getRestaurantTypes() != null && !restaurantDto.getRestaurantTypes().isEmpty()) {
            restaurant.setRestaurantTypes(restaurantDto.getRestaurantTypes().stream()
                    .map(RestaurantTypeMapper::mapToRestaurantTypeEntity)
                    .collect(Collectors.toSet()));
        } else {
            restaurant.setRestaurantTypes(new HashSet<>());
        }

        if (restaurantDto.getDietaryPreferences() != null && !restaurantDto.getDietaryPreferences().isEmpty()) {
            restaurant.setDietaryPreferences(restaurantDto.getDietaryPreferences().stream()
                    .map(DietaryPreferenceMapper::mapToDietaryPreferenceEntity)
                    .collect(Collectors.toSet()));
        } else {
            restaurant.setDietaryPreferences(new HashSet<>());
        }

        if (restaurantDto.getCulinaryOrigins() != null && !restaurantDto.getCulinaryOrigins().isEmpty()) {
            restaurant.setCulinaryOrigins(restaurantDto.getCulinaryOrigins().stream()
                    .map(CulinaryOriginMapper::mapToCulinaryOriginEntity)
                    .collect(Collectors.toSet()));
        } else {
            restaurant.setCulinaryOrigins(new HashSet<>());
        }

        if (restaurantDto.getCulinarySpecialities() != null && !restaurantDto.getCulinarySpecialities().isEmpty()) {
            restaurant.setCulinarySpecialities(restaurantDto.getCulinarySpecialities().stream()
                    .map(CulinarySpecialityMapper::mapToCulinarySpecialityEntity)
                    .collect(Collectors.toSet()));
        } else {
            restaurant.setCulinarySpecialities(new HashSet<>());
        }

        return restaurant;
    }


}

