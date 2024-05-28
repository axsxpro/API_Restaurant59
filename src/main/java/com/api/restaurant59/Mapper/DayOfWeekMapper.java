package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.Model.Entity.DayOfWeek;


public class DayOfWeekMapper {

    //convertit une entité DayOfWeek en un DTO DayOfWeekDTO
    public static DayOfWeekDTO mapToDayOfWeekDTO(DayOfWeek dayOfWeek) {

        // Crée un nouveau DTO DayOfWeekDTO
        DayOfWeekDTO dayOfWeekDto = new DayOfWeekDTO();

        // Affecte l'ID de l'Availability à l'ID du DTO
        dayOfWeekDto.setIdDay(dayOfWeek.getIdDay());
        dayOfWeekDto.setDay(dayOfWeek.getDay());

        // Retourne le DTO AvailabilityDTO
        return dayOfWeekDto;
    }


    //convertit un DTO DayOfWeekDTO en une entité DayOfWeek
    public static DayOfWeek mapToDayOfWeekEntity(DayOfWeekDTO dayOfWeekDTO) {

        //Créer une nouvelle entité DayOfWeek
        DayOfWeek dayOfWeek = new DayOfWeek();

        //Définir les attributs
        dayOfWeek.setIdDay(dayOfWeekDTO.getIdDay());
        dayOfWeek.setDay(dayOfWeekDTO.getDay());

        // Retourne l'entité dayOfWeek
        return dayOfWeek;
    }

}
