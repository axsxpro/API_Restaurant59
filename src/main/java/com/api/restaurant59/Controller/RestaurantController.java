package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.RestaurantDTO;
import com.api.restaurant59.Service.EntityService.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/post/restaurants")
@AllArgsConstructor
public class RestaurantController {

    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDto) {

        RestaurantDTO createdRestaurant = restaurantService.create(restaurantDto);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);

    }


}
