package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Service.EntityService.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/post/city")
@AllArgsConstructor
public class CityController {

    private CityService cityService;

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDto) {

        CityDTO createdCity = cityService.create(cityDto);
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);

    }



}
