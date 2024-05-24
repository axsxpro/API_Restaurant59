package com.api.restaurant59.Mapper;


import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Model.Entity.DayOfWeek;
import com.api.restaurant59.Model.Entity.Schedule;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DayOfWeekMapper {

    //convertit une entité DayOfWeek en un DTO DayOfWeekDTO
    public static DayOfWeekDTO mapToDayOfWeekDTO(DayOfWeek dayOfWeek) {

        // Crée un nouveau DTO DayOfWeekDTO
        DayOfWeekDTO dayOfWeekDto = new DayOfWeekDTO();


        // Affecte l'ID de l'Availability à l'ID du DTO
        dayOfWeekDto.setIdDay(dayOfWeek.getIdDay());
        dayOfWeekDto.setDay(dayOfWeek.getDay());


        if (dayOfWeek.getSchedules() != null && !dayOfWeek.getSchedules().isEmpty()) {

            //
            // set : ensemble d'éléments uniques
            Set<ScheduleDTO> fluxSchedules = dayOfWeek.getSchedules().stream()
                    .map(ScheduleMapper::mapToScheduleDTO) // Utilise le mapper ScheduleMapper pour mapper chaque Schedule à un ScheduleDTO
                    .collect(Collectors.toSet()); // Collecte les ScheduleDTOs dans un ensemble

            // Affecte les schedules au DTO
            dayOfWeekDto.setScheduleDTOS(fluxSchedules);

        } else {

            // Si aucun schedule n'est fourni dans le DTO, initialise un ensemble vide de schedule
            dayOfWeek.setSchedules(new HashSet<>());
        }

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


        if (dayOfWeekDTO.getScheduleDTOS() != null && !dayOfWeekDTO.getScheduleDTOS().isEmpty()) {

            Set<Schedule> fluxSchedules = dayOfWeekDTO.getScheduleDTOS().stream()
                    .map(ScheduleMapper::mapToScheduleEntity)
                    .collect(Collectors.toSet());

            // Affecte les jours à l'entité Availability
            dayOfWeek.setSchedules(fluxSchedules);

        } else {

            // Si aucun jour n'est associé dans le DTO, initialise un ensemble vide de jours
            dayOfWeek.setSchedules(new HashSet<>());
        }


        return dayOfWeek;
    }

}
