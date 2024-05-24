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
    // @RequestBody : indiquer que le corps de la requête HTTP doit être converti en un objet de type CityDTO
    @PostMapping("/create")
    public ResponseEntity<CityDTO> createCity(@RequestBody CityDTO cityDto) {

        // Utilise le service pour créer une nouvelle City à partir du DTO reçu
        CityDTO createdCity = cityService.create(cityDto);
        // Retourne une réponse HTTP avec le DTO de la City créée et un statut 201 (CREATED)
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }


    //récupérer toutes les données d'une entité
    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {

        List<CityDTO> cities = cityService.readAll();
        // Retourne une réponse HTTP statut 200 (OK).
        return ResponseEntity.ok(cities);
    }


    //récupérer par l'id
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable("id") Integer idCity) {

        CityDTO city = cityService.getById(idCity);
        return new ResponseEntity<>(city, HttpStatus.OK); //code 200
        //return ResponseEntity.ok(city);
    }


    //Méthode HTTP PUT : mettre à jour par l'id
    @PutMapping("/update/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable("id") Integer idCity, @RequestBody CityDTO cityDto) {

        CityDTO updatedCity = cityService.update(idCity, cityDto);
        // Retourne une réponse HTTP avec le DTO de la City mise à jour et un statut 202 (ACCEPTED)
        return new ResponseEntity<>(updatedCity, HttpStatus.ACCEPTED);//code 202
    }


    //supprimer par l'id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable("id") Integer idCity) {

        cityService.deleteById(idCity);
        // Retourne une réponse HTTP avec un statut 204 (NO CONTENT) indiquant que la suppression a réussi
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //code 204
    }



}
