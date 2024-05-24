package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RestaurantTypeDTO;
import com.api.restaurant59.Model.Entity.RestaurantType;

public class RestaurantTypeMapper {


    // Méthode pour mapper une entité RestaurantType en un DTO RestaurantTypeDTO
    public static RestaurantTypeDTO mapToRestaurantTypeDTO(RestaurantType restaurantType) {

        // Crée un nouveau DTO RestaurantTypeDTO avec les attributs de l'entité RestaurantType
        return new RestaurantTypeDTO(
                restaurantType.getIdType(),
                restaurantType.getNameType()
        );

    }

    // Méthode pour mapper un DTO RestaurantTypeDTO en une entité RestaurantType
    public static RestaurantType mapToRestaurantTypeEntity(RestaurantTypeDTO restaurantTypeDTO) {

        // Crée une nouvelle entité RestaurantType avec les attributs du DTO RestaurantTypeDTO
        RestaurantType restaurantType = new RestaurantType();

        restaurantType.setIdType(restaurantTypeDTO.getIdType());
        restaurantType.setNameType(restaurantTypeDTO.getNameType());

        return restaurantType;
    }
}

