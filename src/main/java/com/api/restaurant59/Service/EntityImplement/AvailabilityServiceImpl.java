package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.DTO.ScheduleDTO;
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

import java.util.HashSet;
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
        if (availabilityDto.getDaysOfWeek() != null && !availabilityDto.getDaysOfWeek().isEmpty()) {
            Set<DayOfWeek> dayOfWeeks = availabilityDto.getDaysOfWeek().stream().map(dayOfWeekDTO -> {
                // Récupère l'entité DayOfWeek existante
                DayOfWeek dayOfWeek = dayOfWeekRepository.findById(dayOfWeekDTO.getIdDay())
                        .orElseThrow(() -> new EntityNotFoundException("Day not found with id: " + dayOfWeekDTO.getIdDay()));

                // Récupère et associe les schedules existants
                if (dayOfWeekDTO.getSchedules() != null && !dayOfWeekDTO.getSchedules().isEmpty()) {
                    Set<Schedule> schedules = dayOfWeekDTO.getSchedules().stream()
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


        // Cherche l'entité à mettre à jour par son identifiant
        Availability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + id));


        // Si des jours sont présents dans le DTO
        if (updatedAvailabilityDTO.getDaysOfWeek() != null && !updatedAvailabilityDTO.getDaysOfWeek().isEmpty()) {

            // creation d'une collection pour récupérer les jours sous forme d'entité
            Set<DayOfWeek> collectionDaysOfWeek = new HashSet<>();

            // updatedAvailabilityDTO.getDaysOfWeek(): récupération de la collection DaysOfWeeek (qui contient les dayOfWeekDTO)
            // pour chaque élement(dayOfWeekDTO) de la collection on récupère un DTO
            for (DayOfWeekDTO dayOfWeekDTO : updatedAvailabilityDTO.getDaysOfWeek()) {

                // Dans chaque dayOfWeekDTO on recherche par l'id du jour l'entité correspondante qu'on stocke dans la variable dayOfWeek
                DayOfWeek dayOfWeek = dayOfWeekRepository.findById(dayOfWeekDTO.getIdDay())
                        .orElseThrow(() -> new EntityNotFoundException("Day not found with id: " + dayOfWeekDTO.getIdDay()));

                // 1) Met à jour les données (le jour) de l'entité DayOfWeek
                dayOfWeek.setDay(dayOfWeekDTO.getDay());

                // Si dayOfWeekDTO contient des schédules
                if (dayOfWeekDTO.getSchedules() != null && !dayOfWeekDTO.getSchedules().isEmpty()) {

                    // creation d'une collection pour récupérer les schedules sous forme d'entité
                    Set<Schedule> collectionSchedules = new HashSet<>();

                    // dayOfWeekDTO.getSchedules(): récupération de la collection Schedules (qui contient les scheduleDTO)
                    // pour chaque élement(scheduleDTO) de la collection on récupère un DTO
                    for (ScheduleDTO scheduleDTO : dayOfWeekDTO.getSchedules()) {

                        // Dans chaque scheduleDTO on recherche par l'id du schedule l'entité correspondante qu'on stocke dans la variable dayOfWeek
                        Schedule schedule = scheduleRepository.findById(scheduleDTO.getIdSchedule())
                                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + scheduleDTO.getIdSchedule()));

                        //ajout des entités schedule dans la collection de schedules
                        collectionSchedules.add(schedule);
                    }

                    // 2) Ajoute la collection de schedules à l'entité dayOfWeek
                    dayOfWeek.setSchedules(collectionSchedules);
                }

                //ajoute l'entité dayOfWeek dans la collection de dayOfWeek
                collectionDaysOfWeek.add(dayOfWeek);
            }

            // Met à jour la collection des jours de la semaine par une nouvelle collection dans l'entité Availability
            availability.setDays(collectionDaysOfWeek);
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

