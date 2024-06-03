package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.ScheduleDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.ScheduleMapper;
import com.api.restaurant59.Model.Entity.Schedule;
import com.api.restaurant59.Model.Repository.ScheduleRepository;
import com.api.restaurant59.Service.EntityService.ScheduleService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {


    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public ScheduleDTO create(ScheduleDTO scheduleDto) {

        // Mapping du DTO en entité
        Schedule schedule = ScheduleMapper.mapToScheduleEntity(scheduleDto);

        // Sauvegarde de l'entité dans le repository
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Mapping de l'entité sauvegardée en DTO
        ScheduleDTO savedScheduleDto = ScheduleMapper.mapToScheduleDTO(savedSchedule);

        // Retourne le DTO sauvegardé
        return savedScheduleDto;
    }


    @Override
    public List<ScheduleDTO> readAll() {
        // Récupère toutes les entités du repository
        List<Schedule> schedules = scheduleRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return schedules.stream().map(ScheduleMapper::mapToScheduleDTO).collect(Collectors.toList());
    }


    //pagination
    @Override
    public Page<ScheduleDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page, la taille de la page, et le tri par ID croissant.
        Pageable pageable = PageRequest.of(page, size, Sort.by("idSchedule").ascending());
        // Utilise le repository pour trouver toutes les entités Schedule en fonction de l'objet Pageable.
        Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        // Mappe chaque entité Schedule de la page à un ScheduleDTO en utilisant le mapper spécifié.
        return schedulePage.map(ScheduleMapper::mapToScheduleDTO);
    }


    @Override
    public ScheduleDTO getById(Integer id) {
        // Cherche une entité Schedule par son identifiant dans le repository
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        // Si une entité Schedule est trouvée, elle est convertie en ScheduleDTO par le ScheduleMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalSchedule.map(ScheduleMapper::mapToScheduleDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with ID: " + id));
    }

    @Override
    public ScheduleDTO update(Integer id, ScheduleDTO updatedScheduleDto) {

        // Cherche une entité par son identifiant
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule not found with ID: " + id));

        // Mise à jour des propriétés
        schedule.setMorningOpeningTime(updatedScheduleDto.getMorningOpeningTime());
        schedule.setMorningClosingTime(updatedScheduleDto.getMorningClosingTime());
        schedule.setEveningOpeningTime(updatedScheduleDto.getEveningOpeningTime());
        schedule.setEveningClosingTime(updatedScheduleDto.getEveningClosingTime());


        // Sauvegarde de l'entité mise à jour
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return ScheduleMapper.mapToScheduleDTO(updatedSchedule);
    }

    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        scheduleRepository.deleteById(id);
    }

}

