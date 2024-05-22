package com.api.restaurant59.Service.EntityꞮmplement;

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


    private RestaurantRepository restaurantRepository;


    @Override
    public RestaurantDTO create(RestaurantDTO restaurantDto) {

        Restaurant restaurant = RestaurantMapper.mapToRestaurantEntity(restaurantDto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        // Convert User JPA entity to UserDto
        RestaurantDTO savedRestaurantDto = RestaurantMapper.mapToRestaurantDTO(savedRestaurant);

        return savedRestaurantDto;
    }


    public List<RestaurantDTO> readAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(RestaurantMapper::mapToRestaurantDTO).collect(Collectors.toList());
    }


    public RestaurantDTO update(Integer id, RestaurantDTO restaurantDto) {

        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Error! ID not found! :("));;

        // Mise à jour des propriétés du restaurant
        existingRestaurant.setName(restaurantDto.getName());
        existingRestaurant.setAddress(restaurantDto.getAddress());
        existingRestaurant.setAdditionalAddress(restaurantDto.getAdditionalAddress());
        existingRestaurant.setPhone(restaurantDto.getPhone());
        existingRestaurant.setEmail(restaurantDto.getEmail());
        existingRestaurant.setWebsite(restaurantDto.getWebsite());
        existingRestaurant.setSiren(restaurantDto.getSiren());
        existingRestaurant.setIdCity(restaurantDto.getIdCity());
        existingRestaurant.setIdMichelinCategory(restaurantDto.getIdMichelinCategory());
        existingRestaurant.setIdAvailability(restaurantDto.getIdAvailability());

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);

        return RestaurantMapper.mapToRestaurantDTO(updatedRestaurant);
    }


    public RestaurantDTO getById(Integer id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.map(RestaurantMapper::mapToRestaurantDTO).orElse(null);
    }


    public void deleteById(Integer id) {
        restaurantRepository.deleteById(id);
    }


}
