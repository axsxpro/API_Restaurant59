package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.CulinarySpecialityDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.CulinarySpecialityMapper;
import com.api.restaurant59.Model.Entity.CulinarySpeciality;
import com.api.restaurant59.Model.Repository.CulinarySpecialityRepository;
import com.api.restaurant59.Service.EntityService.CulinarySpecialityService;
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
public class CulinarySpecialityServiceImpl implements CulinarySpecialityService {


    private  CulinarySpecialityRepository culinarySpecialityRepository;


    @Override
    public CulinarySpecialityDTO create(CulinarySpecialityDTO culinarySpecialityDto) {
        // Mapping du DTO en entité
        CulinarySpeciality culinarySpeciality = CulinarySpecialityMapper.mapToCulinarySpecialityEntity(culinarySpecialityDto);
        // Sauvegarde de l'entité dans le repository
        CulinarySpeciality savedCulinarySpeciality = culinarySpecialityRepository.save(culinarySpeciality);
        // Mapping de l'entité sauvegardée en DTO
        CulinarySpecialityDTO savedCulinarySpecialityDto = CulinarySpecialityMapper.mapToCulinarySpecialityDTO(savedCulinarySpeciality);
        // Retourne le DTO sauvegardé
        return savedCulinarySpecialityDto;
    }


    @Override
    public List<CulinarySpecialityDTO> readAll() {
        // Récupère toutes les entités du repository
        List<CulinarySpeciality> culinarySpecialities = culinarySpecialityRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return culinarySpecialities.stream().map(CulinarySpecialityMapper::mapToCulinarySpecialityDTO).collect(Collectors.toList());
    }


    //get culinary speciality avec pagination
    @Override
    public Page<CulinarySpecialityDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page, la taille de la page, et le tri par ID croissant.
        Pageable pageable = PageRequest.of(page, size, Sort.by("idSpeciality").ascending());
        // Utilise le repository pour trouver toutes les entités CulinarySpeciality en fonction de l'objet Pageable.
        Page<CulinarySpeciality> culinarySpecialityPage = culinarySpecialityRepository.findAll(pageable);

        // Mappe chaque entité CulinarySpeciality de la page à un CulinarySpecialityDTO en utilisant le mapper spécifié.
        return culinarySpecialityPage.map(CulinarySpecialityMapper::mapToCulinarySpecialityDTO);
    }


    @Override
    public CulinarySpecialityDTO getById(Integer id) {
        // Cherche une entité CulinarySpeciality par son identifiant dans le repository
        Optional<CulinarySpeciality> optionalCulinarySpeciality = culinarySpecialityRepository.findById(id);
        // Si une entité CulinarySpeciality est trouvée, elle est convertie en CulinarySpecialityDTO par le CulinarySpecialityMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalCulinarySpeciality.map(CulinarySpecialityMapper::mapToCulinarySpecialityDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Culinary Speciality not found with ID: " + id));
    }


    @Override
    public CulinarySpecialityDTO update(Integer id, CulinarySpecialityDTO updatedCulinarySpecialityDto) {
        // Cherche une entité par son identifiant
        CulinarySpeciality culinarySpeciality = culinarySpecialityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Culinary Speciality not found with ID: " + id));

        // Mise à jour des propriétés
        culinarySpeciality.setDescription(updatedCulinarySpecialityDto.getDescription());

        // Sauvegarde de l'entité mise à jour
        CulinarySpeciality updatedCulinarySpeciality = culinarySpecialityRepository.save(culinarySpeciality);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return CulinarySpecialityMapper.mapToCulinarySpecialityDTO(updatedCulinarySpeciality);
    }


    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        culinarySpecialityRepository.deleteById(id);
    }

}
