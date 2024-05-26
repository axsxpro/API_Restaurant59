package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.DayOfWeekDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.DayOfWeekMapper;
import com.api.restaurant59.Mapper.ScheduleMapper;
import com.api.restaurant59.Model.Entity.DayOfWeek;
import com.api.restaurant59.Model.Entity.Schedule;
import com.api.restaurant59.Model.Repository.DayOfWeekRepository;
import com.api.restaurant59.Service.EntityService.DayOfWeekService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DayOfWeekServiceImpl implements DayOfWeekService {

    private DayOfWeekRepository dayOfWeekRepository;

    // HTTP POST
    @Override
    public DayOfWeekDTO create(DayOfWeekDTO dayOfWeekDto) {

        // Mapping du DTO en entité
        DayOfWeek dayOfWeek = DayOfWeekMapper.mapToDayOfWeekEntity(dayOfWeekDto);

        // Sauvegarde de l'entité dans le repository
        DayOfWeek savedDayOfWeek = dayOfWeekRepository.save(dayOfWeek);

        // Mapping de l'entité sauvegardée en DTO et Retourne le DTO sauvegardé
        return DayOfWeekMapper.mapToDayOfWeekDTO(savedDayOfWeek);
    }


    // HTTP GET
    @Override
    public List<DayOfWeekDTO> readAll() {

        // Récupère toutes les entités du repository
        List<DayOfWeek> dayOfWeeks = dayOfWeekRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return dayOfWeeks.stream().map(DayOfWeekMapper::mapToDayOfWeekDTO).collect(Collectors.toList());
    }


    // HTTP GET
    @Override
    public DayOfWeekDTO getById(Integer id) {

        // Cherche une entité DayOfWeek par son identifiant dans le repository
        Optional<DayOfWeek> optionalDayOfWeek = dayOfWeekRepository.findById(id);

        // Si une entité DayOfWeek est trouvée, elle est convertie en DayOfWeekDTO par le DayOfWeekMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalDayOfWeek.map(DayOfWeekMapper::mapToDayOfWeekDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Day of Week not found with ID: " + id));
    }


    // HTTP PUT
    @Override
    public DayOfWeekDTO update(Integer id, DayOfWeekDTO updatedDayOfWeekDto) {

        // Cherche une entité par son identifiant
        DayOfWeek dayOfWeek = dayOfWeekRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Day of Week not found with ID: " + id));

        // Mise à jour des propriétés
        dayOfWeek.setDay(updatedDayOfWeekDto.getDay());

        // Sauvegarde de l'entité mise à jour
        DayOfWeek updatedDayOfWeek = dayOfWeekRepository.save(dayOfWeek);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return DayOfWeekMapper.mapToDayOfWeekDTO(updatedDayOfWeek);
    }


    // HTTP DELETE
    @Override
    public void deleteById(Integer id) {

        // Supprime l'entité par son identifiant
        dayOfWeekRepository.deleteById(id);
    }

}

