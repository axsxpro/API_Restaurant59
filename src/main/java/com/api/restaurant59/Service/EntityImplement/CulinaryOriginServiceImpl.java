package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.CulinaryOriginDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.CulinaryOriginMapper;
import com.api.restaurant59.Model.Entity.CulinaryOrigin;
import com.api.restaurant59.Model.Repository.CulinaryOriginRepository;
import com.api.restaurant59.Service.EntityService.CulinaryOriginService;
import lombok.AllArgsConstructor;
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


    //get culinary origin avec pagination
    @Override
    public Page<CulinaryOriginDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page, la taille de la page, et le tri par ID croissant.
        Pageable pageable = PageRequest.of(page, size, Sort.by("idCulinaryOrigin").ascending());
        // Utilise le repository pour trouver toutes les entités CulinaryOrigin en fonction de l'objet Pageable.
        Page<CulinaryOrigin> culinaryOriginPage = culinaryOriginRepository.findAll(pageable);

        // Mappe chaque entité CulinaryOrigin de la page à un CulinaryOriginDTO en utilisant le mapper spécifié.
        return culinaryOriginPage.map(CulinaryOriginMapper::mapToCulinaryOriginDTO);
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
