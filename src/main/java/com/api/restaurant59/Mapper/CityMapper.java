package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Model.Entity.City;

public class CityMapper {

    public static CityDTO mapToCityDTO(City city) {

        return new CityDTO(
                city.getIdCity(),
                city.getCityName(),
                city.getPostalCode(),
                city.getInseeCode()
        );
    }

    public static City mapToCityEntity(CityDTO cityDto) {

        return new City(
                cityDto.getIdCity(),
                cityDto.getCityName(),
                cityDto.getPostalCode(),
                cityDto.getInseeCode()
        );
    }

}

