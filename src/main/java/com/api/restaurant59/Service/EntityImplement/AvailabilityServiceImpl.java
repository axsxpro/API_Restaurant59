package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.AvailabilityMapper;
import com.api.restaurant59.Mapper.DayOfWeekMapper;
import com.api.restaurant59.Model.Entity.Availability;

import com.api.restaurant59.Model.Entity.DayOfWeek;
import com.api.restaurant59.Model.Entity.Schedule;
import com.api.restaurant59.Model.Repository.AvailabilityRepository;
import com.api.restaurant59.Model.Repository.DayOfWeekRepository;
import com.api.restaurant59.Model.Repository.ScheduleRepository;
import com.api.restaurant59.Service.EntityService.AvailabilityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private AvailabilityRepository availabilityRepository;
    private DayOfWeekRepository dayOfWeekRepository;
    private ScheduleRepository scheduleRepository;

    @Override
    public AvailabilityDTO create(AvailabilityDTO availabilityDto) {
        // Mappe le DTO en entité
        Availability availability = AvailabilityMapper.mapToAvailabilityEntity(availabilityDto);

        // Si des jours sont présents dans le DTO, mappe-les en entités
        if (availabilityDto.getDayOfWeekDTOS() != null && !availabilityDto.getDayOfWeekDTOS().isEmpty()) {
            Set<DayOfWeek> dayOfWeeks = availabilityDto.getDayOfWeekDTOS().stream().map(dayOfWeekDTO -> {
                // Récupère l'entité DayOfWeek existante
                DayOfWeek dayOfWeek = dayOfWeekRepository.findById(dayOfWeekDTO.getIdDay())
                        .orElseThrow(() -> new EntityNotFoundException("Day not found with id: " + dayOfWeekDTO.getIdDay()));

                // Récupère et associe les schedules existants
                if (dayOfWeekDTO.getScheduleDTOS() != null && !dayOfWeekDTO.getScheduleDTOS().isEmpty()) {
                    Set<Schedule> schedules = dayOfWeekDTO.getScheduleDTOS().stream()
                            .map(scheduleDTO -> scheduleRepository.findById(scheduleDTO.getIdSchedule())
                                    .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + scheduleDTO.getIdSchedule())))
                            .collect(Collectors.toSet());
                    dayOfWeek.setSchedules(schedules);
                }
                return dayOfWeek;
            }).collect(Collectors.toSet());
            availability.setDays(dayOfWeeks);
        }

        // Sauvegarde l'entité dans le repository
        Availability savedAvailability = availabilityRepository.save(availability);

        // Mappe l'entité sauvegardée en DTO
        return AvailabilityMapper.mapToAvailabilityDTO(savedAvailability);
    }

    @Override
    public List<AvailabilityDTO> readAll() {
        // Récupère toutes les entités du repository
        List<Availability> availabilities = availabilityRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return availabilities.stream()
                .map(AvailabilityMapper::mapToAvailabilityDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AvailabilityDTO getById(Integer id) {
        // Cherche une entité Availability par son identifiant dans le repository
        Optional<Availability> optionalAvailability = availabilityRepository.findById(id);
        // Si une entité Availability est trouvée, la convertit en DTO
        // Sinon, lance une exception ResourceNotFoundException
        return optionalAvailability.map(AvailabilityMapper::mapToAvailabilityDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + id));
    }

    @Override
    public AvailabilityDTO update(Integer id, AvailabilityDTO updatedAvailabilityDTO) {

        // Cherche une entité par son identifiant
        Availability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + id));

        // Si des jours sont présents dans le DTO, mappe-les en entités
        if (updatedAvailabilityDTO.getDayOfWeekDTOS() != null && !updatedAvailabilityDTO.getDayOfWeekDTOS().isEmpty()) {
            Set<DayOfWeek> dayOfWeeks = updatedAvailabilityDTO.getDayOfWeekDTOS().stream()
                    .map(DayOfWeekMapper::mapToDayOfWeekEntity)
                    .collect(Collectors.toSet());
            availability.setDays(dayOfWeeks);
        }

        // Sauvegarde l'entité mise à jour
        Availability updatedAvailability = availabilityRepository.save(availability);

        // Mappe l'entité mise à jour en DTO et la retourne
        return AvailabilityMapper.mapToAvailabilityDTO(updatedAvailability);
    }

    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        availabilityRepository.deleteById(id);
    }
}

