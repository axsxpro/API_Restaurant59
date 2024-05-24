package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.DietaryPreferenceDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.DietaryPreferenceMapper;
import com.api.restaurant59.Model.Entity.DietaryPreference;
import com.api.restaurant59.Model.Repository.DietaryPrefRepository;
import com.api.restaurant59.Service.EntityService.DietaryPreferenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DietaryPrefServiceImpl implements DietaryPreferenceService {


    private DietaryPrefRepository dietaryPreferenceRepository;


    @Override
    public DietaryPreferenceDTO create(DietaryPreferenceDTO dietaryPreferenceDto) {

        // Mapping du DTO en entité
        DietaryPreference dietaryPreference = DietaryPreferenceMapper.mapToDietaryPreferenceEntity(dietaryPreferenceDto);
        // Sauvegarde de l'entité dans le repository
        DietaryPreference savedDietaryPreference = dietaryPreferenceRepository.save(dietaryPreference);
        // Mapping de l'entité sauvegardée en DTO
        DietaryPreferenceDTO savedDietaryPreferenceDto = DietaryPreferenceMapper.mapToDietaryPreferenceDTO(savedDietaryPreference);

        // Retourne le DTO sauvegardé
        return savedDietaryPreferenceDto;
    }



    @Override
    public List<DietaryPreferenceDTO> readAll() {

        // Récupère toutes les entités du repository
        List<DietaryPreference> dietaryPreferences = dietaryPreferenceRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return dietaryPreferences.stream().map(DietaryPreferenceMapper::mapToDietaryPreferenceDTO).collect(Collectors.toList());
    }



    @Override
    public DietaryPreferenceDTO getById(Integer id) {

        // Cherche une entité DietaryPreference par son identifiant dans le repository
        Optional<DietaryPreference> optionalDietaryPreference = dietaryPreferenceRepository.findById(id);

        // Si une entité DietaryPreference est trouvée, elle est convertie en DietaryPreferenceDTO par le DietaryPreferenceMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalDietaryPreference.map(DietaryPreferenceMapper::mapToDietaryPreferenceDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Dietary Preference not found with ID: " + id));
    }


    @Override
    public DietaryPreferenceDTO update(Integer id, DietaryPreferenceDTO updatedDietaryPreferenceDto) {
        // Cherche une entité par son identifiant
        DietaryPreference dietaryPreference = dietaryPreferenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dietary Preference not found with ID: " + id));

        // Mise à jour des propriétés
        dietaryPreference.setDescription(updatedDietaryPreferenceDto.getDescription());

        // Sauvegarde de l'entité mise à jour
        DietaryPreference updatedDietaryPreference = dietaryPreferenceRepository.save(dietaryPreference);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return DietaryPreferenceMapper.mapToDietaryPreferenceDTO(updatedDietaryPreference);
    }


    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        dietaryPreferenceRepository.deleteById(id);
    }

}

