package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Service.EntityService.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/city")
@AllArgsConstructor
public class CityController {

    private CityService cityService;


    //créer une nouvelle instance d'une entité
    @PostMapping("/create")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDto) {

        CityDTO createdCity = cityService.create(cityDto);
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED); //code 201
    }


    //récupérer toutes les données d'une entité
    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {

        List<CityDTO> cities = cityService.readAll();
        return ResponseEntity.ok(cities); //code 200
    }


    //récupérer par l'id
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable("id") Integer idCity) {

        CityDTO city = cityService.getById(idCity);
        return new ResponseEntity<>(city, HttpStatus.OK); //code 200
        //return ResponseEntity.ok(city);
    }


    //mettre à jour par l'id
    @PutMapping("/update/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable("id") Integer idCity, @RequestBody CityDTO cityDto) {

        CityDTO updatedCity = cityService.update(idCity, cityDto);
        return new ResponseEntity<>(updatedCity, HttpStatus.ACCEPTED);//code 202
    }


    //supprimer par l'id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable("id") Integer idCity) {

        cityService.deleteById(idCity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //code 204
    }



}
