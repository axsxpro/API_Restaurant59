package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Model.Entity.City;


public class CityMapper {

    //méthode qui convertit une entité en un objet DTO
    public static CityDTO mapToCityDTO(City city) {

        CityDTO cityDto = new CityDTO(

                city.getIdCity(),
                city.getCityName(),
                city.getInseeCode()
        );

        return cityDto;
    }


    //méthode qui convertit un objet DTO en une entité
    public static City mapToCityEntity(CityDTO cityDto){

        City city = new City(

                cityDto.getIdCity(),
                cityDto.getCityName(),
                cityDto.getInseeCode()
        );

        return city;
    }

}
