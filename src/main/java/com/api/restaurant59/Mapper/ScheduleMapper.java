package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Model.Entity.DayOfWeek;
import com.api.restaurant59.Model.Entity.Schedule;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
public class ScheduleMapper {

    // Convertir une entité Schedule en un DTO ScheduleDTO
    public static ScheduleDTO mapToScheduleDTO(Schedule schedule) {

        // Création d'une entité scheduleDTO
        ScheduleDTO scheduleDTO = new ScheduleDTO();

                scheduleDTO.setIdSchedule(schedule.getIdSchedule()); // ID de l'horaire
                scheduleDTO.setMorningOpeningTime(schedule.getMorningOpeningTime()); // Heure d'ouverture le matin
                scheduleDTO.setMorningClosingTime(schedule.getMorningClosingTime()); // Heure de fermeture le matin
                scheduleDTO.setEveningOpeningTime(schedule.getEveningOpeningTime()); // Heure d'ouverture le soir
                scheduleDTO.setEveningClosingTime(schedule.getEveningClosingTime());// Heure de fermeture le soir

        // Si schedule contient des jours de la semaine
        if (schedule.getDays() != null && !schedule.getDays().isEmpty()) {

            // Création d'une collection pour récupérer les jours sous forme de DTO
            Set<DayOfWeekDTO> daysOfWeek = schedule.getDays().stream()
                    .map(DayOfWeekMapper::mapToDayOfWeekDTO) // Utilise le mapper DayOfWeekMapper pour mapper chaque DayOfWeek à un DayOfWeekDTO
                    .collect(Collectors.toSet()); // Collecte les DayOfWeekDTO dans un ensemble

            // Affecte la collection de jours au DTO
            scheduleDTO.setDaysOfWeek(daysOfWeek);

        } else {

            // Si aucun jour n'est fourni dans l'entité, initialise un ensemble vide de jours
            scheduleDTO.setDaysOfWeek(new HashSet<>());
        }

        return scheduleDTO;

    }



    // Convertir un DTO ScheduleDTO en une entité Schedule
    public static Schedule mapToScheduleEntity(ScheduleDTO scheduleDTO) {

        // Crée une nouvelle entité Schedule
        Schedule schedule = new Schedule();

        schedule.setIdSchedule(scheduleDTO.getIdSchedule()); // Définit l'ID de l'horaire
        schedule.setMorningOpeningTime(scheduleDTO.getMorningOpeningTime()); // Définit l'heure d'ouverture le matin
        schedule.setMorningClosingTime(scheduleDTO.getMorningClosingTime()); // Définit l'heure de fermeture le matin
        schedule.setEveningOpeningTime(scheduleDTO.getEveningOpeningTime()); // Définit l'heure d'ouverture le soir
        schedule.setEveningClosingTime(scheduleDTO.getEveningClosingTime()); // Définit l'heure de fermeture le soir

        // Si scheduleDTO contient des jours de la semaine
        if (scheduleDTO.getDaysOfWeek() != null && !scheduleDTO.getDaysOfWeek().isEmpty()) {

            // Création d'une collection pour récupérer les jours sous forme d'entité
            Set<DayOfWeek> daysOfWeek = scheduleDTO.getDaysOfWeek().stream()
                    .map(DayOfWeekMapper::mapToDayOfWeekEntity)
                    .collect(Collectors.toSet());

            // Affecte les jours à l'entité schedule
            schedule.setDays(daysOfWeek);

        } else {
            // Si aucun jour n'est associé dans le DTO, initialise un ensemble vide de jours
            schedule.setDays(new HashSet<>());
        }

        return schedule; // Retourne l'entité Schedule
    }


}


