package com.api.restaurant59.Service.EntityImplement;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Exception.ResourceNotFoundException;
import com.api.restaurant59.Mapper.RestaurantMapper;
import com.api.restaurant59.Model.Entity.*;
import com.api.restaurant59.Model.Repository.*;
import com.api.restaurant59.Service.EntityService.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {


    private RestaurantRepository restaurantRepository;
    private CityRepository cityRepository;
    private MichelinCategoryRepository michelinCategoryRepository;
    private AvailabilityRepository availabilityRepository;
    private RestaurantTypeRepository restaurantTypeRepository;
    private DietaryPrefRepository dietaryPreferenceRepository;
    private CulinaryOriginRepository culinaryOriginRepository;
    private CulinarySpecialityRepository culinarySpecialityRepository;


    @Override
    public RestaurantDTO create(RestaurantDTO restaurantDto) {

        // Mapping du DTO en entité
        Restaurant restaurant = RestaurantMapper.mapToRestaurantEntity(restaurantDto);

        // Récupérer les entités référencées à partir de leurs identifiants
        City city = cityRepository.findById(restaurantDto.getCity().getIdCity())
                .orElseThrow(() -> new ResourceNotFoundException("City ID not found: " + restaurantDto.getCity().getIdCity()));
        MichelinCategory michelinCategory = michelinCategoryRepository.findById(restaurantDto.getMichelinCategory().getIdMichelinCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Michelin Category ID not found: " + restaurantDto.getMichelinCategory().getIdMichelinCategory()));
        Availability availability = availabilityRepository.findById(restaurantDto.getAvailability().getIdAvailability())
                .orElseThrow(() -> new ResourceNotFoundException("Availability ID not found: " + restaurantDto.getAvailability().getIdAvailability()));

        // Associer les entités récupérées au restaurant
        restaurant.setIdCity(city);
        restaurant.setIdMichelinCategory(michelinCategory);
        restaurant.setIdAvailability(availability);


        // Mappe les collections d'entités many-to-many si elles existent
        if (restaurantDto.getRestaurantTypes() != null && !restaurantDto.getRestaurantTypes().isEmpty()) {

            //creation d'un ensemble qui va contenir des restaurantType entity
            //Set : collection de type restaurantType nommé restaurantTypes
            //stream() : Création du flux
            //map : transformer chaque élément du flux
            Set<RestaurantType> restaurantTypes = restaurantDto.getRestaurantTypes().stream().map(

                    // Expression lambda utilisée pour transformer chaque élément typeDto du flux en un objet RestaurantType
                    // typeDto -> : parametre
                    // Récupère l'entité RestaurantType existante
                    typeDto -> restaurantTypeRepository.findById(typeDto.getIdType())
                            .orElseThrow(() -> new ResourceNotFoundException("Restaurant Type ID not found: " + typeDto.getIdType())))
                    .collect(Collectors.toSet());

            // Met à jour la collection par une nouvelle collection dans l'entité
            restaurant.setRestaurantTypes(restaurantTypes);
        }

        if (restaurantDto.getDietaryPreferences() != null && !restaurantDto.getDietaryPreferences().isEmpty()) {
            Set<DietaryPreference> dietaryPreferences = restaurantDto.getDietaryPreferences().stream()
                    .map(pref -> dietaryPreferenceRepository.findById(pref.getIdDietaryPreference())
                            .orElseThrow(() -> new ResourceNotFoundException("Dietary Preference ID not found: " + pref.getIdDietaryPreference())))
                    .collect(Collectors.toSet());
            restaurant.setDietaryPreferences(dietaryPreferences);
        }

        if (restaurantDto.getCulinaryOrigins() != null && !restaurantDto.getCulinaryOrigins().isEmpty()) {
            Set<CulinaryOrigin> culinaryOrigins = restaurantDto.getCulinaryOrigins().stream()
                    .map(origin -> culinaryOriginRepository.findById(origin.getIdCulinaryOrigin())
                            .orElseThrow(() -> new ResourceNotFoundException("Culinary Origin ID not found: " + origin.getIdCulinaryOrigin())))
                    .collect(Collectors.toSet());
            restaurant.setCulinaryOrigins(culinaryOrigins);
        }

        if (restaurantDto.getCulinarySpecialities() != null && !restaurantDto.getCulinarySpecialities().isEmpty()) {
            Set<CulinarySpeciality> culinarySpecialities = restaurantDto.getCulinarySpecialities().stream()
                    .map(speciality -> culinarySpecialityRepository.findById(speciality.getIdSpeciality())
                            .orElseThrow(() -> new ResourceNotFoundException("Culinary Speciality ID not found: " + speciality.getIdSpeciality())))
                    .collect(Collectors.toSet());
            restaurant.setCulinarySpecialities(culinarySpecialities);
        }

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
    public RestaurantDTO update(Integer id, RestaurantDTO restaurantDto) {

        // récupérer le restaurant que l'on veut update par son identifiant
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant ID not found: " + id));


        // Vérifier si les identifiants de city, michelinCategory et availability ne sont pas null ni vides
        // si non vide ou null: Récupérer les entités référencées à partir de leurs identifiants et associer les entités récupérées au restaurant
        if (restaurantDto.getCity() == null || restaurantDto.getCity().getIdCity() == null) {
            throw new IllegalArgumentException("City ID cannot be null or empty");
        } else {
            City city = cityRepository.findById(restaurantDto.getCity().getIdCity())
                    .orElseThrow(() -> new ResourceNotFoundException("City ID not found: " + restaurantDto.getCity().getIdCity()));
            restaurant.setIdCity(city);
        }


        if (restaurantDto.getMichelinCategory() == null || restaurantDto.getMichelinCategory().getIdMichelinCategory() == null) {
            throw new IllegalArgumentException("Michelin Category ID cannot be null or empty");
        }else {
            MichelinCategory michelinCategory = michelinCategoryRepository.findById(restaurantDto.getMichelinCategory().getIdMichelinCategory())
                    .orElseThrow(() -> new ResourceNotFoundException("Michelin Category ID not found: " + restaurantDto.getMichelinCategory().getIdMichelinCategory()));
            restaurant.setIdMichelinCategory(michelinCategory);
        }


        if (restaurantDto.getAvailability() == null || restaurantDto.getAvailability().getIdAvailability() == null) {
            throw new IllegalArgumentException("Availability ID cannot be null or empty");
        } else {
            Availability availability = availabilityRepository.findById(restaurantDto.getAvailability().getIdAvailability())
                    .orElseThrow(() -> new ResourceNotFoundException("Availability ID not found: " + restaurantDto.getAvailability().getIdAvailability()));
            restaurant.setIdAvailability(availability);
        }


        // Mappe les collections d'entités many-to-many si elles existent
        if (restaurantDto.getRestaurantTypes() != null && !restaurantDto.getRestaurantTypes().isEmpty()) {
            Set<RestaurantType> restaurantTypes = restaurantDto.getRestaurantTypes().stream()
                    .map(typeDto -> restaurantTypeRepository.findById(typeDto.getIdType())
                            .orElseThrow(() -> new ResourceNotFoundException("Restaurant Type ID not found: " + typeDto.getIdType())))
                    .collect(Collectors.toSet());
            restaurant.setRestaurantTypes(restaurantTypes);
        }

        if (restaurantDto.getDietaryPreferences() != null && !restaurantDto.getDietaryPreferences().isEmpty()) {
            Set<DietaryPreference> dietaryPreferences = restaurantDto.getDietaryPreferences().stream()
                    .map(pref -> dietaryPreferenceRepository.findById(pref.getIdDietaryPreference())
                            .orElseThrow(() -> new ResourceNotFoundException("Dietary Preference ID not found: " + pref.getIdDietaryPreference())))
                    .collect(Collectors.toSet());
            restaurant.setDietaryPreferences(dietaryPreferences);
        }

        if (restaurantDto.getCulinaryOrigins() != null && !restaurantDto.getCulinaryOrigins().isEmpty()) {
            Set<CulinaryOrigin> culinaryOrigins = restaurantDto.getCulinaryOrigins().stream()
                    .map(origin -> culinaryOriginRepository.findById(origin.getIdCulinaryOrigin())
                            .orElseThrow(() -> new ResourceNotFoundException("Culinary Origin ID not found: " + origin.getIdCulinaryOrigin())))
                    .collect(Collectors.toSet());
            restaurant.setCulinaryOrigins(culinaryOrigins);
        }

        if (restaurantDto.getCulinarySpecialities() != null && !restaurantDto.getCulinarySpecialities().isEmpty()) {
            Set<CulinarySpeciality> culinarySpecialities = restaurantDto.getCulinarySpecialities().stream()
                    .map(speciality -> culinarySpecialityRepository.findById(speciality.getIdSpeciality())
                            .orElseThrow(() -> new ResourceNotFoundException("Culinary Speciality ID not found: " + speciality.getIdSpeciality())))
                    .collect(Collectors.toSet());
            restaurant.setCulinarySpecialities(culinarySpecialities);
        }

        // Sauvegarde de l'entité mise à jour dans le repository
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // Mapping de l'entité sauvegardée en DTO
        RestaurantDTO savedRestaurantDto = RestaurantMapper.mapToRestaurantDTO(savedRestaurant);

        // Retourne le DTO sauvegardé
        return savedRestaurantDto;
    }


    @Override
    public void deleteById(Integer id) {
        // Supprime l'entité par son identifiant
        restaurantRepository.deleteById(id);
    }

}

