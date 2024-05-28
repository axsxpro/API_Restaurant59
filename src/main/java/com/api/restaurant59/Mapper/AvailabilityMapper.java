package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Model.Entity.Availability;
import com.api.restaurant59.Model.Entity.Schedule;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AvailabilityMapper {



    // Méthode pour mapper une entité Availability vers un DTO AvailabilityDTO
    public static AvailabilityDTO mapToAvailabilityDTO(Availability availability) {

        // Crée un nouveau DTO AvailabilityDTO
        AvailabilityDTO availabilityDTO = new AvailabilityDTO();

        // Affecte l'ID de l'Availability à l'ID du DTO
        availabilityDTO.setIdAvailability(availability.getIdAvailability());

        // Vérifie si des schedules sont associés à l'entité Availability
        if (availability.getSchedules() != null && !availability.getSchedules().isEmpty()) {

            // Création d'une collection pour récupérer les schedules sous forme de DTO, ensemble d'éléments uniques
            Set<ScheduleDTO> fluxSchedule = availability.getSchedules().stream()
                    .map(ScheduleMapper::mapToScheduleDTO) // Utilise le mapper ScheduleMapper pour mapper chaque schedule à un DTO
                    .collect(Collectors.toSet()); // Collecte les schedules dans un ensemble

            // Affecte l'ensemble des schedules au DTO
            availabilityDTO.setSchedules(fluxSchedule);

        } else {
            // Si aucun schedule n'est fourni dans l'entité, initialise un ensemble vide de schedules
            availabilityDTO.setSchedules(new HashSet<>());
        }

        // Retourne le DTO AvailabilityDTO
        return availabilityDTO;
    }



    // Méthode pour mapper un DTO AvailabilityDTO vers une entité Availability
    public static Availability mapToAvailabilityEntity(AvailabilityDTO availabilityDTO) {

        // Crée une nouvelle entité Availability
        Availability availability = new Availability();

        // Affecte l'ID du DTO à l'ID de l'entité
        availability.setIdAvailability(availabilityDTO.getIdAvailability());

        // Vérifie si des schedules sont associés au DTO AvailabilityDTO
        if (availabilityDTO.getSchedules() != null && !availabilityDTO.getSchedules().isEmpty()) {

            // Création d'une collection pour récupérer les schedules sous forme d'entité, ensemble d'éléments uniques
            Set<Schedule> schedules = availabilityDTO.getSchedules().stream()
                    .map(ScheduleMapper::mapToScheduleEntity)
                    .collect(Collectors.toSet());

            // Affecte les schedules à l'entité Availability
            availability.setSchedules(schedules);

        } else {
            // Si aucun schedule n'est associé dans le DTO, initialise un ensemble vide de schedules
            availability.setSchedules(new HashSet<>());
        }

        // Retourne l'entité Availability
        return availability;
    }

}

