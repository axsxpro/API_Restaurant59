package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Model.Entity.City;

import java.util.stream.Collectors;

public class CityMapper {

    // Méthode pour mapper une entité City en DTO CityDTO
    public static CityDTO mapToCityDTO(City city) {

        return new CityDTO(

                city.getIdCity(),
                city.getCityName(),
                city.getPostalCode(),
                city.getInseeCode()
        );
    }


    // Méthode pour mapper un DTO CityDTO en entité City
    public static City mapToCityEntity(CityDTO cityDTO) {

        City city = new City();

        city.setIdCity(cityDTO.getIdCity());
        city.setCityName(cityDTO.getCityName());
        city.setPostalCode(cityDTO.getPostalCode());
        city.setInseeCode(cityDTO.getInseeCode());

        return city;
    }


}

