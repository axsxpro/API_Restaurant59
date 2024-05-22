package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.RestaurantTypeDTO;
import com.api.restaurant59.Model.Entity.RestaurantType;

public class RestaurantTypeMapper {

    public static RestaurantTypeDTO mapToRestaurantTypeDTO(RestaurantType restaurantType) {
        return new RestaurantTypeDTO(
                restaurantType.getIdType(),
                restaurantType.getNameType()
        );
    }

    public static RestaurantType mapToRestaurantTypeEntity(RestaurantTypeDTO restaurantTypeDTO) {
        return new RestaurantType(
                restaurantTypeDTO.getIdType(),
                restaurantTypeDTO.getNameType()
        );
    }
}
