package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.RestaurantMapper;
import com.api.restaurant59.Model.Entity.Restaurant;
import com.api.restaurant59.Model.Repository.RestaurantRepository;
import com.api.restaurant59.Service.EntityService.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public RestaurantDTO create(RestaurantDTO restaurantDto) {
        // Mapping du DTO en entité
        Restaurant restaurant = RestaurantMapper.mapToRestaurantEntity(restaurantDto);
        // Sauvegarde de l'entité dans le repository
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        // Mapping de l'entité sauvegardée en DTO
        RestaurantDTO savedRestaurantDto = RestaurantMapper.mapToRestaurantDTO(savedRestaurant);
        // Retourne le DTO sauvegardé
        return savedRestaurantDto;
    }

    @Override
    public List<RestaurantDTO> readAll() {
        // Récupère toutes les entités du repository
        List<Restaurant> restaurants = restaurantRepository.findAll();
        // Mappe chaque entité en DTO et retourne la liste des DTO
        return restaurants.stream().map(RestaurantMapper::mapToRestaurantDTO).collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO getById(Integer id) {
        // Cherche une entité Restaurant par son identifiant dans le repository
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        // Si une entité Restaurant est trouvée, elle est convertie en RestaurantDTO par le RestaurantMapper
        // Sinon, une exception ResourceNotFoundException est lancée avec un message d'erreur
        return optionalRestaurant.map(RestaurantMapper::mapToRestaurantDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + id));
    }

    @Override
    public RestaurantDTO update(Integer id, RestaurantDTO updatedRestaurantDto) {

        // Cherche une entité par son identifiant
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with ID: " + id));

        // Mise à jour des propriétés
        restaurant.setName(updatedRestaurantDto.getName());
        restaurant.setAddress(updatedRestaurantDto.getAddress());
        restaurant.setAdditionalAddress(updatedRestaurantDto.getAdditionalAddress());
        restaurant.setPhone(updatedRestaurantDto.getPhone());
        restaurant.setEmail(updatedRestaurantDto.getEmail());
        restaurant.setWebsite(updatedRestaurantDto.getWebsite());
        restaurant.setSiren(updatedRestaurantDto.getSiren());

        //propriétés many to One
        restaurant.setIdCity(updatedRestaurantDto.getIdCity());
        restaurant.setIdMichelinCategory(updatedRestaurantDto.getIdMichelinCategory());
        restaurant.setIdAvailability(updatedRestaurantDto.getIdAvailability());

        // Sauvegarde de l'entité mise à jour
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        // Mapping de l'entité mise à jour en DTO et la retourne
        return RestaurantMapper.mapToRestaurantDTO(updatedRestaurant);
    }

    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        restaurantRepository.deleteById(id);
    }
}

