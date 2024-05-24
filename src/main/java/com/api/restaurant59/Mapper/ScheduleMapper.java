package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Model.Entity.Schedule;

import java.util.stream.Collectors;

public class ScheduleMapper {

    // convertir une entité Schedule en un DTO ScheduleDTO
    public static ScheduleDTO mapToScheduleDTO(Schedule schedule) {

        //creation d'une entité scheduleDTO
        return new ScheduleDTO(

                //récupération des informations de l'entité
                schedule.getIdSchedule(), // ID de l'horaire
                schedule.getMorningOpeningTime(), // Heure d'ouverture le matin
                schedule.getMorningClosingTime(), // Heure de fermeture le matin
                schedule.getEveningOpeningTime(), // Heure d'ouverture le soir
                schedule.getEveningClosingTime()// Heure de fermeture le soir
        );
    }

    // convertir un DTO ScheduleDTO en une entité Schedule
    public static Schedule mapToScheduleEntity(ScheduleDTO scheduleDTO) {

        // Crée une nouvelle entité Schedule
        Schedule schedule = new Schedule();

        schedule.setIdSchedule(scheduleDTO.getIdSchedule()); // Définit l'ID de l'horaire
        schedule.setMorningOpeningTime(scheduleDTO.getMorningOpeningTime()); // Définit l'heure d'ouverture le matin
        schedule.setMorningClosingTime(scheduleDTO.getMorningClosingTime()); // Définit l'heure de fermeture le matin
        schedule.setEveningOpeningTime(scheduleDTO.getEveningOpeningTime()); // Définit l'heure d'ouverture le soir
        schedule.setEveningClosingTime(scheduleDTO.getEveningClosingTime()); // Définit l'heure de fermeture le soir

        return schedule; // Retourne l'entité Schedule
    }

}

