package com.api.restaurant59.Controller;

import com.api.restaurant59.DTO.CityDTO;
import com.api.restaurant59.Service.EntityService.CityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/city")
@AllArgsConstructor
public class CityController {


    @Autowired
    private CityService cityService;


    //créer une nouvelle instance d'une entité
    //@RequestBody : indiquer que le corps de la requête HTTP doit être converti en un objet de type CityDTO
    @PostMapping("/create")
    @Operation(summary = "Create new city")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDto) {

        // Utilise le service pour créer une nouvelle City à partir du DTO reçu
        CityDTO createdCity = cityService.create(cityDto);
        // Retourne une réponse HTTP avec le DTO de la City créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }


    //récupérer toutes les données d'une entité
    @GetMapping
    @Operation(summary = "Get all cities")
    public ResponseEntity<List<CityDTO>> getAllCities() {

        List<CityDTO> cities = cityService.readAll();
        // Retourne une réponse HTTP statut 200 (OK).
        return ResponseEntity.ok(cities);
    }


    //récupérer toutes les villes avec pagination
    @GetMapping("/pagination")
    @Operation(summary = "Get All cities with pagination")
    public ResponseEntity<Page<CityDTO>> getAllCitiesWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Page<CityDTO> cities = cityService.readAll(page, size);

        return ResponseEntity.ok(cities);
    }


    //récupérer par l'id
    @GetMapping("/{id}")
    @Operation(summary = "Get city by ID")
    public ResponseEntity<CityDTO> getCityById(@PathVariable("id") Integer idCity) {

        CityDTO city = cityService.getById(idCity);
        return new ResponseEntity<>(city, HttpStatus.OK); //code 200
        //return ResponseEntity.ok(city);
    }


    //Méthode HTTP PUT : mettre à jour par l'id
    @PutMapping("/update/{id}")
    @Operation(summary = "Update city by ID")
    public ResponseEntity<CityDTO> updateCity(@PathVariable("id") Integer idCity, @RequestBody CityDTO cityDto) {

        CityDTO updatedCity = cityService.update(idCity, cityDto);
        // Retourne une réponse HTTP avec le DTO de la City mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedCity, HttpStatus.ACCEPTED);//code 202
    }


    //supprimer par l'id
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete city by ID")
    public ResponseEntity<Void> deleteCity(@PathVariable("id") Integer idCity) {

        cityService.deleteById(idCity);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //code 204
    }


}
