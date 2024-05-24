package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.MichelinCategoryDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.MichelinCategoryMapper;
import com.api.restaurant59.Model.Entity.MichelinCategory;
import com.api.restaurant59.Model.Repository.MichelinCategoryRepository;
import com.api.restaurant59.Service.EntityService.MichelinCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MichelinCategoryServiceImpl implements MichelinCategoryService {


    private MichelinCategoryRepository michelinCategoryRepository;


    @Override
    public MichelinCategoryDTO create(MichelinCategoryDTO michelinCategoryDto) {
        // Mapping du DTO en entité
        MichelinCategory michelinCategory = MichelinCategoryMapper.mapToMichelinCategoryEntity(michelinCategoryDto);
        // Sauvegarde de l'entité dans le repository
        MichelinCategory savedMichelinCategory = michelinCategoryRepository.save(michelinCategory);
        // Mapping de l'entité sauvegardée en DTO
        MichelinCategoryDTO savedMichelinCategoryDto = MichelinCategoryMapper.mapToMichelinCategoryDTO(savedMichelinCategory);
        // Retourne le DTO sauvegardé
        return savedMichelinCategoryDto;
    }


    @Override
    public List<MichelinCategoryDTO> readAll() {
        // Récupère toutes les entités du repository
        List<MichelinCategory> michelinCategories = michelinCategoryRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return michelinCategories.stream().map(MichelinCategoryMapper::mapToMichelinCategoryDTO).collect(Collectors.toList());
    }


    @Override
    public MichelinCategoryDTO getById(Integer id) {
        // Cherche une entité MichelinCategory par son identifiant dans le repository
        Optional<MichelinCategory> optionalMichelinCategory = michelinCategoryRepository.findById(id);
        // Si une entité MichelinCategory est trouvée, elle est convertie en MichelinCategoryDTO par le MichelinCategoryMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalMichelinCategory.map(MichelinCategoryMapper::mapToMichelinCategoryDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Michelin Category not found with ID: " + id));
    }


    @Override
    public MichelinCategoryDTO update(Integer id, MichelinCategoryDTO updatedMichelinCategoryDto) {
        // Cherche une entité par son identifiant
        MichelinCategory michelinCategory = michelinCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Michelin Category not found with ID: " + id));

        // Mise à jour des propriétés
        michelinCategory.setCategoryName(updatedMichelinCategoryDto.getCategoryName());

        // Sauvegarde de l'entité mise à jour
        MichelinCategory updatedMichelinCategory = michelinCategoryRepository.save(michelinCategory);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return MichelinCategoryMapper.mapToMichelinCategoryDTO(updatedMichelinCategory);
    }


    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        michelinCategoryRepository.deleteById(id);
    }


}

