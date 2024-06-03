package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.RestaurantTypeDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.RestaurantTypeMapper;
import com.api.restaurant59.Model.Entity.RestaurantType;
import com.api.restaurant59.Model.Repository.RestaurantTypeRepository;
import com.api.restaurant59.Service.EntityService.RestaurantTypeService;
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
public class RestaurantTypeServiceImpl implements RestaurantTypeService {

    @Autowired
    private RestaurantTypeRepository restaurantTypeRepository;


    @Override
    public RestaurantTypeDTO create(RestaurantTypeDTO restaurantTypeDto) {
        // Mapping du DTO en entité
        RestaurantType restaurantType = RestaurantTypeMapper.mapToRestaurantTypeEntity(restaurantTypeDto);
        // Sauvegarde de l'entité dans le repository
        RestaurantType savedRestaurantType = restaurantTypeRepository.save(restaurantType);
        // Mapping de l'entité sauvegardée en DTO
        RestaurantTypeDTO savedRestaurantTypeDto = RestaurantTypeMapper.mapToRestaurantTypeDTO(savedRestaurantType);
        // Retourne le DTO sauvegardé
        return savedRestaurantTypeDto;
    }

    @Override
    public List<RestaurantTypeDTO> readAll() {
        // Récupère toutes les entités du repository
        List<RestaurantType> restaurantTypes = restaurantTypeRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return restaurantTypes.stream().map(RestaurantTypeMapper::mapToRestaurantTypeDTO).collect(Collectors.toList());
    }


    //pagination
    @Override
    public Page<RestaurantTypeDTO> readAll(int page, int size) {

        // Crée un objet Pageable avec le numéro de page et la taille de la page spécifiés.
        Pageable pageable = PageRequest.of(page, size, Sort.by("idType").ascending());

        // Utilise le repository pour trouver toutes les entités RestaurantType en fonction de l'objet Pageable.
        Page<RestaurantType> restaurantTypePage = restaurantTypeRepository.findAll(pageable);

        // Mappe chaque entité Schedule de la page à un RestaurantTypeDTO en utilisant le mapper spécifié.
        return restaurantTypePage.map(RestaurantTypeMapper::mapToRestaurantTypeDTO);
    }


    @Override
    public RestaurantTypeDTO getById(Integer id) {
        // Cherche une entité RestaurantType par son identifiant dans le repository
        Optional<RestaurantType> optionalRestaurantType = restaurantTypeRepository.findById(id);
        // Si une entité RestaurantType est trouvée, elle est convertie en RestaurantTypeDTO par le RestaurantTypeMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalRestaurantType.map(RestaurantTypeMapper::mapToRestaurantTypeDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Type not found with ID: " + id));
    }


    @Override
    public RestaurantTypeDTO update(Integer id, RestaurantTypeDTO updatedRestaurantTypeDto) {
        // Cherche une entité par son identifiant
        RestaurantType restaurantType = restaurantTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Type not found with ID: " + id));

        // Mise à jour des propriétés
        restaurantType.setNameType(updatedRestaurantTypeDto.getNameType());

        // Sauvegarde de l'entité mise à jour
        RestaurantType updatedRestaurantType = restaurantTypeRepository.save(restaurantType);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return RestaurantTypeMapper.mapToRestaurantTypeDTO(updatedRestaurantType);
    }


    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        restaurantTypeRepository.deleteById(id);
    }
}
