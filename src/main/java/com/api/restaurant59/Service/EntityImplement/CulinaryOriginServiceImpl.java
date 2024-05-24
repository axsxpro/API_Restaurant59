package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.CulinaryOriginDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.CulinaryOriginMapper;
import com.api.restaurant59.Model.Entity.CulinaryOrigin;
import com.api.restaurant59.Model.Repository.CulinaryOriginRepository;
import com.api.restaurant59.Service.EntityService.CulinaryOriginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CulinaryOriginServiceImpl implements CulinaryOriginService {


    private CulinaryOriginRepository culinaryOriginRepository;


    @Override
    public CulinaryOriginDTO create(CulinaryOriginDTO culinaryOriginDto) {
        // Mapping du DTO en entité
        CulinaryOrigin culinaryOrigin = CulinaryOriginMapper.mapToCulinaryOriginEntity(culinaryOriginDto);
        // Sauvegarde de l'entité dans le repository
        CulinaryOrigin savedCulinaryOrigin = culinaryOriginRepository.save(culinaryOrigin);
        // Mapping de l'entité sauvegardée en DTO
        // Retourne le DTO sauvegardé
        return CulinaryOriginMapper.mapToCulinaryOriginDTO(savedCulinaryOrigin);
    }


    @Override
    public List<CulinaryOriginDTO> readAll() {
        // Récupère toutes les entités du repository
        List<CulinaryOrigin> culinaryOrigins = culinaryOriginRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return culinaryOrigins.stream().map(CulinaryOriginMapper::mapToCulinaryOriginDTO).collect(Collectors.toList());
    }


    @Override
    public CulinaryOriginDTO getById(Integer id) {
        // Cherche une entité CulinaryOrigin par son identifiant dans le repository
        Optional<CulinaryOrigin> optionalCulinaryOrigin = culinaryOriginRepository.findById(id);
        // Si une entité CulinaryOrigin est trouvée, elle est convertie en CulinaryOriginDTO par le CulinaryOriginMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalCulinaryOrigin.map(CulinaryOriginMapper::mapToCulinaryOriginDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Culinary Origin not found with ID: " + id));
    }


    @Override
    public CulinaryOriginDTO update(Integer id, CulinaryOriginDTO updatedCulinaryOriginDto) {
        // Cherche une entité par son identifiant
        CulinaryOrigin culinaryOrigin = culinaryOriginRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Culinary Origin not found with ID: " + id));

        // Mise à jour des propriétés
        culinaryOrigin.setCountry(updatedCulinaryOriginDto.getCountry());

        // Sauvegarde de l'entité mise à jour
        CulinaryOrigin updatedCulinaryOrigin = culinaryOriginRepository.save(culinaryOrigin);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return CulinaryOriginMapper.mapToCulinaryOriginDTO(updatedCulinaryOrigin);
    }


    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        culinaryOriginRepository.deleteById(id);
    }

}
