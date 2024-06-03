package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.AvailabilityDTO;
import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.AvailabilityMapper;
import com.api.restaurant59.Model.Entity.Availability;
import com.api.restaurant59.Model.Entity.DayOfWeek;
import com.api.restaurant59.Model.Entity.Schedule;
import com.api.restaurant59.Model.Repository.AvailabilityRepository;
import com.api.restaurant59.Model.Repository.DayOfWeekRepository;
import com.api.restaurant59.Model.Repository.ScheduleRepository;
import com.api.restaurant59.Service.EntityService.AvailabilityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;
    private DayOfWeekRepository dayOfWeekRepository;
    private ScheduleRepository scheduleRepository;


    @Override
    public AvailabilityDTO create(AvailabilityDTO availabilityDto) {


        // Mappe le DTO en entité
        Availability availability = AvailabilityMapper.mapToAvailabilityEntity(availabilityDto);

        // Si des jours sont présents dans le DTO, mapper en entités
        if (availabilityDto.getSchedules() != null && !availabilityDto.getSchedules().isEmpty()) {

            Set<Schedule> schedules = availabilityDto.getSchedules().stream().map( scheduleDTO -> {

                // Récupère l'entité schedule existante
                Schedule schedule = scheduleRepository.findById(scheduleDTO.getIdSchedule())
                                .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + scheduleDTO.getIdSchedule()));

                schedule.setIdSchedule(scheduleDTO.getIdSchedule());

                // Récupère et associe les jours existants
                if (scheduleDTO.getDaysOfWeek() != null && !scheduleDTO.getDaysOfWeek().isEmpty()) {

                    Set<DayOfWeek> daysOfWeek = scheduleDTO.getDaysOfWeek().stream()
                            .map(dayOfWeekDTO -> dayOfWeekRepository.findById(dayOfWeekDTO.getIdDay())
                                    .orElseThrow(() -> new EntityNotFoundException("Day not found with id: " + dayOfWeekDTO.getIdDay())))
                            .collect(Collectors.toSet());

                    schedule.setDays(daysOfWeek);
                }
                return schedule;

            }).collect(Collectors.toSet());

            availability.setSchedules(schedules);
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
    public Page<AvailabilityDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page et la taille de la page spécifiés.
        Pageable pageable = PageRequest.of(page, size);
        // Utilise le repository pour trouver toutes les entités Availability en fonction de l'objet Pageable.
        Page<Availability> availabilityPage = availabilityRepository.findAll(pageable);
        // Mappe chaque entité Availability de la page à un AvailabilityDTO en utilisant le mapper spécifié.
        return availabilityPage.map(AvailabilityMapper::mapToAvailabilityDTO);
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

        // Si des schedules sont présents dans le DTO
        if (updatedAvailabilityDTO.getSchedules() != null && !updatedAvailabilityDTO.getSchedules().isEmpty()) {

            // Création d'une collection pour récupérer les schedules sous forme d'entité
            Set<Schedule> collectionSchedules = new HashSet<>();

            // updatedAvailabilityDTO.getSchedules(): récupération de la collection Schedules (qui contient les scheduleDTO)
            // pour chaque élément(scheduleDTO) de la collection on récupère un DTO
            for (ScheduleDTO scheduleDTO : updatedAvailabilityDTO.getSchedules()) {

                // Dans chaque scheduleDTO on recherche par l'id de l'horaire l'entité correspondante qu'on stocke dans la variable schedule
                Schedule schedule = scheduleRepository.findById(scheduleDTO.getIdSchedule())
                        .orElseThrow(() -> new EntityNotFoundException("Schedule not found with id: " + scheduleDTO.getIdSchedule()));

                // 1) Met à jour les données (les horaires) de l'entité Schedule
                schedule.setIdSchedule(scheduleDTO.getIdSchedule());

                // Si scheduleDTO contient des jours de la semaine
                if (scheduleDTO.getDaysOfWeek() != null && !scheduleDTO.getDaysOfWeek().isEmpty()) {

                    // Création d'une collection pour récupérer les jours de la semaine sous forme d'entité
                    Set<DayOfWeek> collectionDaysOfWeek = new HashSet<>();

                    // scheduleDTO.getDaysOfWeek(): récupération de la collection DaysOfWeek (qui contient les dayOfWeekDTO)
                    // pour chaque élément(dayOfWeekDTO) de la collection on récupère un DTO
                    for (DayOfWeekDTO dayOfWeekDTO : scheduleDTO.getDaysOfWeek()) {

                        // Dans chaque dayOfWeekDTO on recherche par l'id du jour l'entité correspondante qu'on stocke dans la variable dayOfWeek
                        DayOfWeek dayOfWeek = dayOfWeekRepository.findById(dayOfWeekDTO.getIdDay())
                                .orElseThrow(() -> new EntityNotFoundException("Day not found with id: " + dayOfWeekDTO.getIdDay()));

                        // Ajout des entités DayOfWeek dans la collection de jours de la semaine
                        collectionDaysOfWeek.add(dayOfWeek);
                    }

                    // 2) Ajoute la collection de jours de la semaine à l'entité Schedule
                    schedule.setDays(collectionDaysOfWeek);
                }

                // Ajoute l'entité Schedule dans la collection de schedules
                collectionSchedules.add(schedule);
            }

            // Met à jour la collection des horaires par une nouvelle collection dans l'entité Availability
            availability.setSchedules(collectionSchedules);
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

