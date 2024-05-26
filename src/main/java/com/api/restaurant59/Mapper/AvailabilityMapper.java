package com.api.restaurant59.Mapper;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.Model.Entity.Availability;
import com.api.restaurant59.Model.Entity.DayOfWeek;

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

        if (availability.getDays() != null && !availability.getDays().isEmpty()) {

            // creation d'une collection pour récupérer les availabilities sous forme de DTO, ensemble d'éléments uniques
            Set<DayOfWeekDTO> fluxDayOfWeek = availability.getDays().stream()
                    .map(DayOfWeekMapper::mapToDayOfWeekDTO) // Utilise le mapper DayOfWeekMapper pour mapper chaque jour à un DTO
                    .collect(Collectors.toSet()); // Collecte les jours dans un ensemble

            // Affecte l'ensemble des jours au DTO
            availabilityDTO.setDaysOfWeek(fluxDayOfWeek);

        } else {
            // Si aucun jour n'est fourni dans le DTO, initialise un ensemble vide de jours
            availability.setDays(new HashSet<>());
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

        // Vérifie si des jours sont associés à l'entité AvailabilityDTO
        if (availabilityDTO.getDaysOfWeek() != null && !availabilityDTO.getDaysOfWeek().isEmpty()) {

            // creation d'une collection pour récupérer les availabilities sous forme d'entité, ensemble d'éléments uniques
            Set<DayOfWeek> daysOfWeek = availabilityDTO.getDaysOfWeek().stream()
                    .map(DayOfWeekMapper::mapToDayOfWeekEntity)
                    .collect(Collectors.toSet());

            // Affecte les jours à l'entité Availability
            availability.setDays(daysOfWeek);

        } else {

            // Si aucun jour n'est associé dans le DTO, initialise un ensemble vide de jours
            availability.setDays(new HashSet<>());
        }

        // Retourne l'entité Availability
        return availability;
    }


}
